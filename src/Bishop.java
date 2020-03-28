public class Bishop extends Pieces {
    public Bishop(int x, int y, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        super(x, y, peiceForm, pieceAttention, isPeiceWhite,type,nickName,PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2,StringBuilder move,StringBuilder killSentence) {
        if (Math.abs(x-position1)==Math.abs(y - position2)){
            int i = x;
            int j = y;
            int numberOfDisBlankHomes = 0;
            while (i!=position1 && j!=position2){
                if (x<position1)
                    i++;
                if (x>position1)
                    i--;
                if (y>position2)
                    j--;
                else
                    j++;
                if (i==position1 && j ==position2)
                    break;
                if (Table.isInThisHomeAnyPiece(i,j)!=null)
                    numberOfDisBlankHomes+=1;
            }
            if (numberOfDisBlankHomes==0 && Table.isInThisHomeAnyPiece(position1,position1)==null){
                move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                GameMenu.allMoves.add(move.toString());
                x = position1;
                 y = position2;
                System.out.println("moved");
                 return true;
            }
            if (numberOfDisBlankHomes>0) {
                System.out.println("cannot move to the spot");
                return false;
            }
            if (numberOfDisBlankHomes==0 && Table.isInThisHomeAnyPiece(position1,position2)!=null){
                if (Table.isInThisHomeAnyPiece(position1, position2).isPeiceWhite == isPeiceWhite){
                    System.out.println("cannot move to the spot");
                    return false;
                }
                else {
                    if (kill(position1, position2,move,killSentence)){
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean kill(int position1, int position2,StringBuilder move,StringBuilder killSentence) {
        return super.kill(position1,position2,move,killSentence);
    }

    @Override
    protected boolean undo(int position1, int position2) {
        return super.undo(position1, position2);
    }
}


