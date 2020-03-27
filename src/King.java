public class King extends Pieces{
    public King(int x, int y, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        super(x, y, peiceForm, pieceAttention, isPeiceWhite,type,nickName,PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2,StringBuilder move) {
       if (Math.abs(x-position1)==1 && y==position2){
           if (Table.isInThisHomeAnyPiece(position1, position2)==null){
               move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
               GameMenu.allMoves.add(move.toString());
               x=position1;
               y=position2;
               System.out.println("moved");
               return true;
           }
           if (Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite==isPeiceWhite){
               System.out.println("cannot move to the spot");
               return false;
           }
           else {
               if (kill(position1, position2,move))
                   return true;
               return false;
           }
       }
       if (Math.abs(y-position2)==1 && x == position1){
           if (Table.isInThisHomeAnyPiece(position1, position2)==null){
               move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
               GameMenu.allMoves.add(move.toString());
               x=position1;
               y=position2;
               System.out.println("moved");
               return true;
           }
           if (Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite==isPeiceWhite){
               System.out.println("cannot move to the spot");
               return false;
           }
           else {
               if (kill(position1, position2,move))
                   return true;
               return false;
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
