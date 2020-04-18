public class Pawn extends Pieces {
    public Pawn(int x, int y, PieceForm pieceForm, PieceAttention pieceAttention, boolean isPeiceWhite, String type, String nickName, PieceFormatView pieceFormatView) {
        super(x, y, pieceForm, pieceAttention, isPeiceWhite, type, nickName, PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2, StringBuilder move, StringBuilder killSentence) {
        if (isPieceWhite) {
            if (turn == 1) {
                if (Math.abs(position2 - y) == 1 && position1 - x == 1) {
                    if (kill(position1, position2, move, killSentence)) {
                        turn += 1;
                        return true;
                    } else if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    return false;

                }
                if (position2 == y && position1 - x == 2) {
                    if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                        if (Table.isInThisHomeAnyPiece(position1 - 1, position2) == null) {
                            move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                            GameMenu.allMoves.add(move.toString());
                            setLastY(y);
                            setLastX(x);
                            x = position1;
                            System.out.println("moved");
                            turn += 1;
                            return true;
                        }
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    System.out.println("cannot move to the spot");
                    return false;
                }
            }
            if (Math.abs(position2 - y) == 1 && position1 - x == 1) {
                if (kill(position1, position2, move, killSentence)) {
                    turn += 1;
                    return true;
                } else if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                    System.out.println("cannot move to the spot");
                    return false;
                }
                return false;
            }
            if (y == position2 && position1 - x == 1) {
                if (Table.isInThisHomeAnyPiece(position1, y) == null) {
                    move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                    GameMenu.allMoves.add(move.toString());
                    setLastY(y);
                    setLastX(x);
                    x = position1;
                    System.out.println("moved");
                    turn += 1;
                    return true;
                }
                System.out.println("cannot move to the spot");
                return false;
            }
        } else {
            if (turn == 1) {
                if (Math.abs(position2 - y) == 1 && x - position1 == 1) {
                    if (kill(position1, position2, move, killSentence)) {
                        turn += 1;
                        return true;
                    } else if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    return false;
                }
                if (position2 == y && x - position1 == 2) {
                    if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                        if (Table.isInThisHomeAnyPiece(position1 + 1, position2) == null) {
                            move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                            GameMenu.allMoves.add(move.toString());
                            setLastY(y);
                            setLastX(x);
                            x = position1;
                            System.out.println("moved");
                            turn += 1;
                            return true;
                        }
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    System.out.println("cannot move to the spot");
                    return false;
                }
            }
            if (Math.abs(y - position2) == 1 && x - position1 == 1) {
                if (kill(position1, position2, move, killSentence)) {
                    turn += 1;
                    return true;
                } else if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                    System.out.println("cannot move to the spot");
                    return false;
                }
                return false;
            }
            if (y == position2 && x - position1 == 1) {
                if (Table.isInThisHomeAnyPiece(position1, position2) == null) {
                    move.append(" ").append(x).append(",").append(y).append(" to ").append(position1).append(",").append(position2);
                    GameMenu.allMoves.add(move.toString());
                    setLastX(x);
                    setLastY(y);
                    x = position1;
                    System.out.println("moved");
                    turn += 1;
                    return true;
                }
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
