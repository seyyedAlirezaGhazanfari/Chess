public class Rook extends Pieces {
    public Rook(int x, int y, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        super(x, y, peiceForm, pieceAttention, isPeiceWhite,type,nickName,PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2,StringBuilder move) {
     if (position1==x && Math.abs(position2-y)>0){
         int j = y;
         int numberOfUnBlankHomesInWay =0 ;
         while (j!=position2){
             if (y<position2){
                 j--;
             }
             if (y>position2)
                 j+=1;
             if (j==position2)
                 break;
             if (Table.isInThisHomeAnyPiece(x,j) != null)
                 numberOfUnBlankHomesInWay+=1;
         }
         if (numberOfUnBlankHomesInWay==0 && Table.isInThisHomeAnyPiece(position1, position2)==null){
             move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
             GameMenu.allMoves.add(move.toString());
             y  = position2;
             System.out.println("moved");
             return true;
         }
         if (numberOfUnBlankHomesInWay==0 &&Table.isInThisHomeAnyPiece(position1,position2)!=null){
             if (isPeiceWhite==Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite){
                 System.out.println("cannot move to the spot");
                 return false;
             }
             else {
                 if (kill(position1, position2,move))
                     return true;
                 return false;
             }
         }
         if (numberOfUnBlankHomesInWay>0) {
             System.out.println("cannot move to the spot");
             return false;
         }
     }
     if (position2==y && Math.abs(position1-x)>0){
         int i = x;
         int numberOfUnBlankHomesInWay =0 ;
         while (i!=position1){
             if (x<position1){
                 i--;
             }
             if (x>position1)
                 i+=1;
             if (i==position1)
                 break;
             if (Table.isInThisHomeAnyPiece(i,y) != null)
                 numberOfUnBlankHomesInWay+=1;
         }
         if (numberOfUnBlankHomesInWay==0 && Table.isInThisHomeAnyPiece(position1, position2)==null){
             move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
             GameMenu.allMoves.add(move.toString());
             x = position1;
             System.out.println("moved");
             return true;
         }
         if (numberOfUnBlankHomesInWay==0 &&Table.isInThisHomeAnyPiece(position1,position2)!=null){
             if (isPeiceWhite==Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite){
                 System.out.println("cannot move to the spot");
                 return false;
             }
             else {
                 if (kill(position1, position2,move))
                     return true;
                 return false;
             }
         }
         if (numberOfUnBlankHomesInWay>0) {
             System.out.println("cannot move to the spot");
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
