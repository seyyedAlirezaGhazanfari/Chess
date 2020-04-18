import java.util.Objects;

public class Knight extends Pieces {
    public Knight(int x, int y, PieceForm pieceForm, PieceAttention pieceAttention, boolean isPeiceWhite, String type, String nickName, PieceFormatView pieceFormatView) {
        super(x, y, pieceForm, pieceAttention, isPeiceWhite, type, nickName, PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        if (Math.abs(position1 - x) == 2 && Math.abs(position2 - y) == 1) {
            if (Table.isInThisHomeAnyPiece(position1, position2) != null) {
                if (isPieceWhite == Objects.requireNonNull(Table.isInThisHomeAnyPiece(position1, position2)).isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                }
                return kill(position1, position2, move, killSentence);
            }
            if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                GameMenu.allMoves.add(move.toString());
                setLastY(y);
                setLastX(x);
                x = position1;
                y = position2;
                System.out.println("moved");
                return true;
            }
        }
        if (Math.abs(position1 - x) == 1 && Math.abs(position2 - y) == 2) {
            if (Table.isInThisHomeAnyPiece(position1, position2) != null) {
                if (isPieceWhite == Objects.requireNonNull(Table.isInThisHomeAnyPiece(position1, position2)).isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                }
                return kill(position1, position2, move, killSentence);
            }
            if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                GameMenu.allMoves.add(move.toString());
                setLastY(y);
                setLastX(x);
                x = position1;
                y = position2;
                System.out.println("moved");
                return true;
            }
        }
        System.out.println("cannot move to the spot");
        return false;
    }

    @Override
    protected boolean kill(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        return super.kill(position1, position2, move, killSentence);
    }

    @Override
    protected boolean undo(int position1, int position2) {
        return super.undo(position1, position2);
    }
}
