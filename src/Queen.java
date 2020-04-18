import java.util.Objects;

public class Queen extends Pieces {
    public Queen(int x, int y, PieceForm pieceForm, PieceAttention pieceAttention, boolean isPeiceWhite, String type, String nickName, PieceFormatView pieceFormatView) {
        super(x, y, pieceForm, pieceAttention, isPeiceWhite, type, nickName, PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        if (Math.abs(x - position1) == Math.abs(y - position2)) {
            int i = x;
            int j = y;
            int numberOfDisBlankHomes = 0;
            while (i != position1 && j != position2) {
                if (x < position1)
                    i++;
                if (x > position1)
                    i--;
                if (y > position2)
                    j--;
                if (y < position2)
                    j++;
                if (i == position1 && j == position2)
                    break;
                if (Table.isInThisHomeAnyPiece(i, j) != null)
                    numberOfDisBlankHomes += 1;
            }
            if (numberOfDisBlankHomes == 0 && Table.isInThisHomeAnyPiece(position1, position2) == null) {
                move.append(" " + String.valueOf(x) + "," + String.valueOf(y) + " to " + String.valueOf(position1) + "," + String.valueOf(position2));
                GameMenu.allMoves.add(move.toString());
                setLastY(y);
                setLastX(x);
                x = position1;
                y = position2;
                System.out.println("moved");
                return true;
            }
            if (numberOfDisBlankHomes > 0) {
                System.out.println("cannot move to the spot");
                return false;
            }
            if (numberOfDisBlankHomes == 0 && Table.isInThisHomeAnyPiece(position1, position2) != null) {
                if (Objects.requireNonNull(Table.isInThisHomeAnyPiece(position1, position2)).isPieceWhite == isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                } else {
                    return kill(position1, position2, move, killSentence);
                }
            }
        }
        if (position1 == x && Math.abs(position2 - y) > 0) {
            int j = y;
            int numberOfUnBlankHomesInWay = 0;
            while (j != position2) {
                if (y < position2) {
                    j++;
                }
                if (y > position2)
                    j--;
                if (j == position2)
                    break;
                if (Table.isInThisHomeAnyPiece(x, j) != null)
                    numberOfUnBlankHomesInWay += 1;
            }
            if (numberOfUnBlankHomesInWay == 0 && Table.isInThisHomeAnyPiece(position1, position2) == null) {
                move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                GameMenu.allMoves.add(move.toString());
                setLastY(y);
                setLastX(x);
                y = position2;
                System.out.println("moved");
                return true;
            }
            if (numberOfUnBlankHomesInWay == 0 && Table.isInThisHomeAnyPiece(position1, position2) != null) {
                if (isPieceWhite == Objects.requireNonNull(Table.isInThisHomeAnyPiece(position1, position2)).isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                } else {
                    return kill(position1, position2, move, killSentence);
                }
            }
            if (numberOfUnBlankHomesInWay > 0) {
                System.out.println("cannot move to the spot");
                return false;
            }
        }
        if (position2 == y && Math.abs(position1 - x) > 0) {
            int i = x;

            int numberOfUnBlankHomesInWay = 0;
            while (i != position1) {
                if (x < position1) {
                    i++;
                }
                if (x > position1)
                    i--;
                if (i == position1)
                    break;
                if (Table.isInThisHomeAnyPiece(i, y) != null)
                    numberOfUnBlankHomesInWay += 1;
            }
            if (numberOfUnBlankHomesInWay == 0 && Table.isInThisHomeAnyPiece(position1, position2) == null) {
                move.append(" " + String.valueOf(x) + "," + String.valueOf(y) + " to " + String.valueOf(position1) + "," + String.valueOf(position2));
                GameMenu.allMoves.add(move.toString());
                setLastY(y);
                setLastX(x);
                x = position1;
                System.out.println("moved");
                return true;
            }
            if (numberOfUnBlankHomesInWay == 0 && Table.isInThisHomeAnyPiece(position1, position2) != null) {
                if (isPieceWhite == Objects.requireNonNull(Table.isInThisHomeAnyPiece(position1, position2)).isPieceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                } else {
                    return kill(position1, position2, move, killSentence);
                }
            }
            if (numberOfUnBlankHomesInWay > 0) {
                System.out.println("cannot move to the spot");
                return false;
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
