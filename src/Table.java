import java.util.Objects;

public class Table {
    private static Players player1;
    private static Players players2;

    public Table(Players player1, Players players2) {
        player1.getAllPieces().clear();
        players2.getAllPieces().clear();
        Table.player1 = player1;
        Table.players2 = players2;
    }

    public void makeTable() {
        for (int i = 1; i <= 8; i++) {
            if (i == 2) {
                for (int j = 1; j <= 8; j++) {
                    Pawn soldier = new Pawn(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "Pawn", "Pw", PieceFormatView.FUTURE);
                    player1.getAllPieces().add(soldier);
                }
            }
            if (i == 1) {
                for (int j = 1; j <= 8; j++) {
                    if (j == 8 || j == 1) {
                        Rook rook = new Rook(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "Rook", "Rw", PieceFormatView.FUTURE);
                        player1.getAllPieces().add(rook);
                    }
                    if (j == 2 || j == 7) {
                        Knight horse = new Knight(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "Knight", "Nw", PieceFormatView.FUTURE);
                        player1.getAllPieces().add(horse);
                    }
                    if (j == 3 || j == 6) {
                        Bishop elephant = new Bishop(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "Bishop", "Bw", PieceFormatView.FUTURE);
                        player1.getAllPieces().add(elephant);
                    }
                    if (j == 4) {
                        Queen counselor = new Queen(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "Queen", "Qw", PieceFormatView.FUTURE);
                        player1.getAllPieces().add(counselor);
                    }
                    if (j == 5) {
                        King king = new King(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, true, "King", "Kw", PieceFormatView.FUTURE);
                        player1.getAllPieces().add(king);
                    }
                }

            }
            if (i == 7) {
                for (int j = 1; j <= 8; j++) {
                    Pawn soldier = new Pawn(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "Pawn", "Pb", PieceFormatView.FUTURE);
                    players2.getAllPieces().add(soldier);
                }
            }
            if (i == 8) {
                for (int j = 1; j <= 8; j++) {
                    if (j == 8 || j == 1) {
                        Rook rook = new Rook(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "Rook", "Rb", PieceFormatView.FUTURE);
                        players2.getAllPieces().add(rook);
                    }
                    if (j == 2 || j == 7) {
                        Knight horse = new Knight(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "Knight", "Nb", PieceFormatView.FUTURE);
                        players2.getAllPieces().add(horse);
                    }
                    if (j == 3 || j == 6) {
                        Bishop elephant = new Bishop(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "Bishop", "Bb", PieceFormatView.FUTURE);
                        players2.getAllPieces().add(elephant);
                    }
                    if (j == 4) {
                        Queen counselor = new Queen(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "Queen", "Qb", PieceFormatView.FUTURE);
                        players2.getAllPieces().add(counselor);
                    }
                    if (j == 5) {
                        King king = new King(i, j, PieceForm.DESELECTED, PieceAttention.ALIVE, false, "King", "Kb", PieceFormatView.FUTURE);
                        players2.getAllPieces().add(king);
                    }
                }
            }
        }
    }

    public static Pieces isInThisHomeAnyPiece(int x, int y) {
        for (Pieces piece : player1.getAllPieces()) {
            if (piece.x == x && piece.y == y)
                return piece;
        }
        for (Pieces piece : players2.getAllPieces()) {
            if (piece.x == x && piece.y == y)
                return piece;
        }
        return null;
    }

    public static void PrintTable() {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                if (Pieces.findNickNameByPosition(i, j) == null) {
                    System.out.print("  |");
                } else if (Pieces.findNickNameByPosition(i, j) != null && Objects.requireNonNull(Pieces.findNickNameByPosition(i, j)).pieceFormatView.equals(PieceFormatView.FUTURE) && Pieces.findNickNameByPosition(i, j).pieceAttention.equals(PieceAttention.ALIVE)) {
                    if (Pieces.findNickNameByPosition(i, j) != null) {
                        System.out.format("%s|", Objects.requireNonNull(Pieces.findNickNameByPosition(i, j)).nickName);
                    }
                }

            }
            System.out.print("\n");
        }
    }

    public static void restartingTable() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (isInThisHomeAnyPiece(i, j) != null) {
                    Pieces piece = Pieces.getPieceByPosition(i, j);
                    if (piece != null) {
                        piece.x = 0;
                        piece.y = 0;
                    }
                }
            }
        }
    }
}
