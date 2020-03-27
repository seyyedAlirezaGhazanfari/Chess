public class Knight extends Pieces {
    public Knight(int x, int y, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        super(x, y, peiceForm, pieceAttention, isPeiceWhite,type,nickName,PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2,StringBuilder move) {
        if (Math.abs(position1-x)==2 && Math.abs(position2-y)==1){
            if ( Table.isInThisHomeAnyPiece(position1, position2)!=null){
                if (isPeiceWhite==Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite){
                    System.out.println("cannot move to the spot");
                    return false;
                }
                if (kill(position1,position2,move))
                    return true;
                return false;
            }
            if ( Table.isInThisHomeAnyPiece(position1, position2)==null){
                move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                GameMenu.allMoves.add(move.toString());
                x = position1;
                y= position2;
                System.out.println("moved");
                return true;
            }
        }
        if (Math.abs(position1-x)==1 && Math.abs(position2-y)==2){
            if ( Table.isInThisHomeAnyPiece(position1, position2)!=null){
                if (isPeiceWhite==Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite) {
                    System.out.println("cannot move to the spot");
                    return false;
                }
                if (kill(position1,position2,move))
                    return true;
                return false;
            }
            if ( Table.isInThisHomeAnyPiece(position1, position2)==null){
                move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                GameMenu.allMoves.add(move.toString());
                x = position1;
                y= position2;
                System.out.println("moved");
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean kill(int position1, int position2,StringBuilder move) {
        return super.kill(position1, position2,move);
    }

    @Override
    protected boolean undo(int position1, int position2) {
        return super.undo(position1, position2);
    }
}
