import java.util.ArrayList;
import java.util.Objects;

public class GameMenu {
    private static Players players1;
    private static Players player2;
    private static int turn;
    private static boolean isThereAMoveInThisTurn;
    private static boolean isThereAUndoInThisTurn;
    public static boolean isThereAKillInThisTurn;
    private static String moveSubmission;
    public static ArrayList<String> allMoves;
    private static StringBuilder killSentence;
    private static int limit;
    private static Table table;
    private static int position1;
    private static int position2;
    private static Pieces key;

    public GameMenu(Participator participator, Participator participator2, int limit) {
        players1 = new Players(participator);
        player2 = new Players(participator2);
        players1.getMovements().clear();
        player2.getMovements().clear();

        GameMenu.limit = limit;
        turn = 1;
        table = new Table(players1, player2);
        table.makeTable();
        allMoves = new ArrayList<>();
        isThereAMoveInThisTurn = false;
        Players.allKilledPiecesNickNamesOfPlayerBlack = new ArrayList<>();
        Players.allKilledPiecesNickNamesOfPlayerWhite = new ArrayList<>();
    }

    public static boolean select(int x, int y) {
        if (x > 8 || y > 8 || x < 1 || y < 1) {
            System.out.println("wrong coordination");
            return false;
        }
        if (turn % 2 != 0) {
            if (Table.isInThisHomeAnyPiece(x, y) != null && !Objects.requireNonNull(Pieces.getPieceByPosition(x, y)).isPieceWhite) {
                System.out.println("you can only select one of your pieces");
                return false;
            }
            if (Table.isInThisHomeAnyPiece(x, y) == null) {
                System.out.println("no piece on this spot");
                return false;
            }
            disSelectAllPieces(players1);
            for (Pieces piece : players1.getAllPieces()) {
                if (piece.x == x && piece.y == y) {
                    piece.pieceForm = PieceForm.SELECTED;
                    System.out.println("selected");
                    return true;
                }
            }
        } else {

            if (Table.isInThisHomeAnyPiece(x, y) != null && Objects.requireNonNull(Pieces.getPieceByPosition(x, y)).isPieceWhite) {
                System.out.println("you can only select one of your pieces");
                return false;
            }

            if (Table.isInThisHomeAnyPiece(x, y) == null) {
                System.out.println("no piece on this spot");
                return false;
            }
            disSelectAllPieces(player2);
            for (Pieces piece : player2.getAllPieces()) {
                if (piece.x == x && piece.y == y) {
                    piece.pieceForm = PieceForm.SELECTED;
                    System.out.println("selected");
                    return true;
                }
            }
        }
        return false;
    }

    public static void deselect() {
        if (turn % 2 != 0) {
            for (Pieces piece : players1.getAllPieces()) {
                if (piece.pieceForm.equals(PieceForm.SELECTED)) {
                    piece.pieceForm = PieceForm.DESELECTED;
                    System.out.println("deselected");
                    return;
                }
            }
        } else {
            for (Pieces piece : player2.getAllPieces()) {
                if (piece.pieceForm.equals(PieceForm.SELECTED)) {
                    piece.pieceForm = PieceForm.DESELECTED;
                    System.out.println("deselected");
                    return;
                }
            }
        }
        System.out.println("no piece is selected");
    }

    public static boolean move(String n1, String n2) {
        StringBuilder move = new StringBuilder();
        killSentence = new StringBuilder();
        if (isThereAMoveInThisTurn) {
            System.out.println("already moved");
            return false;
        }
        if (n1.length() > 1 || n2.length() > 1) {
            System.out.println("wrong coordination");
            return false;
        }
        int x = Integer.parseInt(n1);
        int y = Integer.parseInt(n2);
        if (x > 8 || x < 1 || y < 1 || y > 8) {
            System.out.println("wrong coordination");
            return false;
        }

        if (turn % 2 != 0) {
            for (Pieces piece : players1.getAllPieces()) {
                if (piece.pieceForm.equals(PieceForm.SELECTED)) {
                    if (pieceTypeChoose(x, y, piece, move, killSentence)) {
                        isThereAMoveInThisTurn = true;
                        players1.getMovements().add(move.toString());
                        moveSubmission = move.toString();
                        key = piece;
                        position1 = x;
                        position2 = y;
                        return true;
                    }
                    return false;
                }
            }
        } else {
            for (Pieces piece : player2.getAllPieces()) {
                if (piece.pieceForm.equals(PieceForm.SELECTED)) {

                    if (pieceTypeChoose(x, y, piece, move, killSentence)) {
                        isThereAMoveInThisTurn = true;
                        player2.getMovements().add(move.toString());
                        moveSubmission = move.toString();
                        key = piece;
                        position1 = x;
                        position2 = y;
                        return true;
                    }
                    return false;
                }

            }
        }
        System.out.println("do not have any selected piece");
        return false;
    }

    public static boolean nextTurn() {
        if (!isThereAMoveInThisTurn) {
            System.out.println("you must move then proceed to next turn");
            return false;
        }
        isThereAMoveInThisTurn = false;
        isThereAUndoInThisTurn = false;
        Players winnerPlayer;
        Players loserPlayer;
        if (turn % 2 == 0) {
            for (Pieces piece : players1.getAllPieces()) {
                if (piece.pieceAttention.equals(PieceAttention.KILLED)) {
                    if (piece.nickName.equals("Kw")) {
                        System.out.println("turn completed");
                        System.out.println("player " + player2.getParticipator().getUsername() + " with color black won");
                        winnerPlayer = player2;
                        loserPlayer = players1;
                        winnerPlayer.getParticipator().getPlayerInfo().setWinNumber(winnerPlayer.getParticipator().getPlayerInfo().getWinNumber() + 1);
                        winnerPlayer.getParticipator().getPlayerInfo().setScore(winnerPlayer.getParticipator().getPlayerInfo().getScore() + 3);
                        loserPlayer.getParticipator().getPlayerInfo().setLoseNumber(loserPlayer.getParticipator().getPlayerInfo().getLoseNumber() + 1);
                        Players.allKilledPiecesNickNamesOfPlayerBlack.clear();
                        Players.allKilledPiecesNickNamesOfPlayerWhite.clear();
                        Players.allKilledPerson.clear();
                        piece.pieceFormatView = PieceFormatView.PAST;
                        piece.pieceForm = PieceForm.DESELECTED;
                        piece.x = 0;
                        piece.y = 0;
                        Table.restartingTable();
                        player2.getAllPieces().clear();
                        players1.getAllPieces().clear();
                        return true;
                    }
                    piece.pieceFormatView = PieceFormatView.PAST;
                    piece.pieceForm = PieceForm.DESELECTED;
                    piece.x = 0;
                    piece.y = 0;
                }
            }
        } else {
            for (Pieces piece : player2.getAllPieces()) {
                if (piece.pieceAttention.equals(PieceAttention.KILLED)) {
                    if (piece.nickName.equals("Kb")) {
                        System.out.println("turn completed");
                        System.out.println("player " + players1.getParticipator().getUsername() + " with color white won");
                        winnerPlayer = players1;
                        loserPlayer = player2;
                        winnerPlayer.getParticipator().getPlayerInfo().setWinNumber(winnerPlayer.getParticipator().getPlayerInfo().getWinNumber() + 1);
                        winnerPlayer.getParticipator().getPlayerInfo().setScore(winnerPlayer.getParticipator().getPlayerInfo().getScore() + 3);
                        loserPlayer.getParticipator().getPlayerInfo().setLoseNumber(loserPlayer.getParticipator().getPlayerInfo().getLoseNumber() + 1);
                        Players.allKilledPiecesNickNamesOfPlayerBlack.clear();
                        Players.allKilledPiecesNickNamesOfPlayerWhite.clear();
                        Players.allKilledPerson.clear();
                        piece.pieceFormatView = PieceFormatView.PAST;
                        piece.pieceForm = PieceForm.DESELECTED;
                        piece.x = 0;
                        piece.y = 0;
                        Table.restartingTable();
                        player2.getAllPieces().clear();
                        players1.getAllPieces().clear();

                        return true;
                    }
                    piece.pieceFormatView = PieceFormatView.PAST;
                    piece.pieceForm = PieceForm.DESELECTED;
                    piece.x = 0;
                    piece.y = 0;
                }
            }
        }
        if (turn == limit) {
            System.out.println("turn completed");
            System.out.println("draw");
            players1.getParticipator().getPlayerInfo().setEqualNumber(players1.getParticipator().getPlayerInfo().getEqualNumber() + 1);
            player2.getParticipator().getPlayerInfo().setEqualNumber(player2.getParticipator().getPlayerInfo().getEqualNumber() + 1);
            player2.getParticipator().getPlayerInfo().setScore(player2.getParticipator().getPlayerInfo().getScore() + 1);
            players1.getParticipator().getPlayerInfo().setScore(players1.getParticipator().getPlayerInfo().getScore() + 1);
            Players.allKilledPiecesNickNamesOfPlayerBlack.clear();
            Players.allKilledPiecesNickNamesOfPlayerWhite.clear();
            Players.allKilledPerson.clear();
            Table.restartingTable();
            player2.getAllPieces().clear();
            players1.getAllPieces().clear();
            return true;
        }

        disSelectAllPieces(player2);
        disSelectAllPieces(players1);
        System.out.println("turn completed");
        turn += 1;


        return false;
    }

    public static void showTurn() {
        String username;
        String color;
        if (turn % 2 == 0) {
            username = player2.getParticipator().getUsername();
            color = "black";
        } else {
            username = players1.getParticipator().getUsername();
            color = "white";
        }
        System.out.println("it is player " + username + " turn with color " + color);
    }

    public static boolean undo() {
        if (turn % 2 == 0) {
            if (player2.getUndo() == 0) {
                System.out.println("you cannot undo anymore");
                return false;
            }
            if (!isThereAMoveInThisTurn) {
                System.out.println("you must move before undo");
                return false;
            }
            if (isThereAUndoInThisTurn) {
                System.out.println("you have used your undo for this turn");
                return false;
            }
            player2.setUndo(player2.getUndo() - 1);
            player2.getMovements().remove(moveSubmission);
            allMoves.remove(moveSubmission);
            Players.allKilledPiecesNickNamesOfPlayerWhite.remove(killSentence.toString());
        } else {
            if (players1.getUndo() == 0) {
                System.out.println("you cannot undo anymore");
                return false;
            }
            if (!isThereAMoveInThisTurn) {
                System.out.println("you must move before undo");
                return false;
            }
            if (isThereAUndoInThisTurn) {
                System.out.println("you have used your undo for this turn");
                return false;
            }
            players1.setUndo(players1.getUndo() - 1);
            players1.getMovements().remove(moveSubmission);
            allMoves.remove(moveSubmission);
            Players.allKilledPiecesNickNamesOfPlayerBlack.remove(killSentence.toString());
        }
        System.out.println("undo completed");
        undoProcess(key.lastX, key.lastY, key);
        Players.allKilledPerson.remove(killSentence.toString());
        isThereAUndoInThisTurn = true;
        isThereAMoveInThisTurn = false;
        return true;
    }

    public static void showUndoNumber() {
        if (turn % 2 != 0) {
            System.out.println("you have " + players1.getUndo() + " undo moves");
        } else {
            System.out.println("you have " + player2.getUndo() + " undo moves");
        }

    }

    public static void showMovesOfThisPlayer() {
        if (turn % 2 != 0) {
            for (String movment : players1.getMovements()) {
                System.out.println(movment);
            }

        } else {
            for (String movment : player2.getMovements()) {
                System.out.println(movment);
            }

        }
    }

    public static void showMoveAll() {
        for (String move : allMoves) {
            System.out.println(move);
        }

    }

    public static void showKilledPieceOfThisPlayer() {
        if (turn % 2 == 0) {
            for (String s : Players.allKilledPiecesNickNamesOfPlayerBlack) {
                System.out.println(s);
            }

        } else {
            for (String s : Players.allKilledPiecesNickNamesOfPlayerWhite) {
                System.out.println(s);
            }


        }
    }

    public static void showKilledAll() {
        for (String person : Players.allKilledPerson) {
            System.out.println(person);
        }
    }

    public static void help() {
        System.out.println("select [x],[y]\ndeselect\nmove [x],[y]\nnext_turn\nshow_turn\nundo\nundo_number\nshow_moves [-all]\nshow_killed [-all]\nshow_board\nhelp\nforfeit");
    }

    public static void disSelectAllPieces(Players player) {
        for (Pieces piece : player.getAllPieces()) {
            piece.pieceForm = PieceForm.DESELECTED;

        }
    }

    public static boolean pieceTypeChoose(int x, int y, Pieces piece, StringBuilder move, StringBuilder killSentence) {
        if (piece.pieceForm.equals(PieceForm.SELECTED)) {
            switch (piece.type) {
                case "Pawn":
                    Pawn pawn = (Pawn) piece;
                    move.append(pawn.nickName);
                    return pawn.move(x, y, move, killSentence);
                case "Rook":
                    Rook rook = (Rook) piece;
                    move.append(rook.nickName);
                    return rook.move(x, y, move, killSentence);
                case "Queen":
                    Queen queen = (Queen) piece;
                    move.append(queen.nickName);
                    return queen.move(x, y, move, killSentence);
                case "King":
                    King king = (King) piece;
                    move.append(king.nickName);
                    return king.move(x, y, move, killSentence);
                case "Knight":
                    Knight knight = (Knight) piece;
                    move.append(knight.nickName);
                    return knight.move(x, y, move, killSentence);
                case "Bishop":
                    Bishop bishop = (Bishop) piece;
                    move.append(bishop.nickName);
                    return bishop.move(x, y, move, killSentence);
            }
        }
        return false;
    }

    public static boolean undoProcess(int x, int y, Pieces piece) {
        switch (piece.type) {
            case "Pawn":
                Pawn pawn = (Pawn) piece;
                pawn.turn -= 1;
                return pawn.undo(x, y);
            case "Rook":
                Rook rook = (Rook) piece;
                return rook.undo(x, y);
            case "Queen":
                Queen queen = (Queen) piece;
                return queen.undo(x, y);
            case "King":
                King king = (King) piece;
                return king.undo(x, y);
            case "Knight":
                Knight knight = (Knight) piece;
                return knight.undo(x, y);
            case "Bishop":
                Bishop bishop = (Bishop) piece;
                return bishop.undo(x, y);
        }

        return false;
    }

    public static void forfeit() {
        System.out.println("you have forfeited");
        Players winnerPlayer;
        Players loserPlayer;
        if (turn % 2 == 0) {
            System.out.format("player %s with color white won\n", players1.getParticipator().getUsername());
            winnerPlayer = players1;
            loserPlayer = player2;
        } else {
            System.out.format("player %s with color black won\n", player2.getParticipator().getUsername());
            winnerPlayer = player2;
            loserPlayer = players1;
        }
        winnerPlayer.getParticipator().getPlayerInfo().setScore(winnerPlayer.getParticipator().getPlayerInfo().getScore() + 2);
        loserPlayer.getParticipator().getPlayerInfo().setScore(loserPlayer.getParticipator().getPlayerInfo().getScore() - 1);
        winnerPlayer.getParticipator().getPlayerInfo().setWinNumber(winnerPlayer.getParticipator().getPlayerInfo().getWinNumber() + 1);
        loserPlayer.getParticipator().getPlayerInfo().setLoseNumber(loserPlayer.getParticipator().getPlayerInfo().getLoseNumber() + 1);
        Players.allKilledPiecesNickNamesOfPlayerBlack.clear();
        Players.allKilledPiecesNickNamesOfPlayerWhite.clear();
        Players.allKilledPerson.clear();
        Table.restartingTable();

    }
}
