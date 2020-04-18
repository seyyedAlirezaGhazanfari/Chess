import java.util.ArrayList;


public class Pieces {
    public static ArrayList<Pieces> allPieces = new ArrayList<>();
    protected int x;
    protected int y;
    protected int lastX;
    protected int lastY;
    protected int turn;
    private Pieces killedPiece;
    protected String type;
    protected boolean isPieceWhite;
    protected PieceForm pieceForm;
    protected PieceAttention pieceAttention;
    protected String nickName;
    protected PieceFormatView pieceFormatView;

    public Pieces(int x, int y, PieceForm pieceForm, PieceAttention pieceAttention, boolean isPieceWhite, String type, String nickName, PieceFormatView pieceFormatView) {
        this.x = x;
        this.y = y;
        this.pieceForm = PieceForm.DESELECTED;
        this.pieceAttention = PieceAttention.ALIVE;
        this.pieceFormatView = PieceFormatView.FUTURE;
        allPieces.add(this);
        this.isPieceWhite = isPieceWhite;
        this.type = type;
        this.nickName = nickName;
        killedPiece = null;
        turn = 1;
    }


    protected boolean move(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        return true;
    }

    protected boolean kill(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
            return false;
        }
        for (Pieces piece : allPieces) {
            if (piece.x == position1 && piece.y == position2) {
                if (piece.isPieceWhite == isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                } else {
                    if (piece.isPieceWhite) {
                        killSentence.append(piece.nickName).append(" killed in spot ").append(piece.x).append(",").append(piece.y);
                        Players.allKilledPiecesNickNamesOfPlayerWhite.add(killSentence.toString());
                    } else {
                        killSentence.append(piece.nickName).append(" killed in spot ").append(piece.x).append(",").append(piece.y);
                        Players.allKilledPiecesNickNamesOfPlayerBlack.add(killSentence.toString());
                    }
                    killedPiece = Pieces.findNickNameByPosition(position1, position2);
                    Players.allKilledPerson.add(piece.nickName + " killed in spot " + piece.x + "," + piece.y);
                    piece.pieceFormatView = PieceFormatView.NOW;
                    piece.pieceAttention = PieceAttention.KILLED;
                    killedPiece.pieceFormatView = PieceFormatView.NOW;
                    killedPiece.pieceAttention = PieceAttention.KILLED;
                    move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2).append(" destroyed ").append(piece.nickName);
                    GameMenu.allMoves.add(move.toString());
                    setLastX(x);
                    setLastY(y);
                    x = position1;
                    y = position2;
                    System.out.println("rival piece destroyed");
                    GameMenu.isThereAKillInThisTurn = true;
                    return true;
                }
            }
        }
        System.out.println("cannot move to the spot");
        return false;
    }

    protected static Pieces findNickNameByPosition(int position1, int position2) {
        for (Pieces piece : allPieces) {
            if (piece.x == position1 && position2 == piece.y && piece.pieceFormatView.equals(PieceFormatView.FUTURE)) {
                return piece;
            }
        }
        return null;
    }

    protected boolean undo(int position1, int position2) {
        for (Pieces piece : allPieces) {
            if (piece.pieceFormatView.equals(PieceFormatView.NOW) || piece.pieceAttention.equals(PieceAttention.KILLED)) {
                piece.pieceFormatView = PieceFormatView.FUTURE;
                piece.pieceAttention = PieceAttention.ALIVE;
                break;
            }
        }
        if (killedPiece != null) {
            killedPiece.pieceAttention = PieceAttention.ALIVE;
            killedPiece.pieceFormatView = PieceFormatView.FUTURE;
        }
        for (String s : GameMenu.allMoves) {
            if (s.equals(nickName + " " + String.valueOf(position1) + "," + String.valueOf(position2) + " to " + String.valueOf(x) + "," + String.valueOf(y))) {
                allPieces.remove(s);
                break;
            }
        }
        setLastY(y);
        setLastX(x);
        x = position1;
        y = position2;
        if (killedPiece == null)
            return true;
        killedPiece.x = lastX;
        killedPiece.y = lastY;
        return true;
    }


    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public static Pieces getPieceByPosition(int position1, int position2) {
        for (Pieces piece : allPieces) {
            if (piece.x == position1 && piece.y == position2)
                return piece;
        }
        return null;
    }
}

