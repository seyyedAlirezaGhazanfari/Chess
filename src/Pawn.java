public class Pawn extends Pieces{
    public Pawn(int x, int y, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        super(x, y, peiceForm, pieceAttention, isPeiceWhite,type,nickName,PieceFormatView.FUTURE);
    }

    @Override
    protected boolean move(int position1, int position2,StringBuilder move) {
        if (isPeiceWhite){
            if (turn == 1) {
                if (Math.abs(position1-x)==1 && position2-y==1){
                    if(kill(position1,position2,move)){
                        turn+=1;
                        return true;
                    }
                    return false;

                }
                if (position1 == x && position2-y==2){
                    if (Table.isInThisHomeAnyPiece(x,position2) == null){
                        if (Table.isInThisHomeAnyPiece(x,position2-1)==null){
                            move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                           Players.allmoves.add(move.toString());
                            y= position2;
                            System.out.println("moved");
                            turn+=1;
                            return true;
                        }
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    System.out.println("cannot move to the spot");
                    return false;
                }
            }
            if (Math.abs(position1-x)==1 && position2-y==1){
                if (kill(position1,position2,move)){
                    turn+=1;
                    return true;
                }
                return false;
            }
            if (x==position1 && position2 - y == 1){
                if (Table.isInThisHomeAnyPiece(x,position2)==null){
                    move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                    Players.allmoves.add(move.toString());
                    y= position2;
                    System.out.println("moved");
                    turn+=1;
                    return true;
                }
                System.out.println("cannot move to the spot");
                return false;
            }
        }
        else {
            if (turn == 1){
                if (Math.abs(position1-x) ==1 && y - position2 == 1){
                    if (kill(position1,position2,move)){
                        turn+=1;
                        return true;
                    }
                    return false;
                }
                if (position1 == x && y-position2==2){
                    if (Table.isInThisHomeAnyPiece(x,position2)==null){
                        if (Table.isInThisHomeAnyPiece(x,position2+1)==null){
                            move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                            Players.allmoves.add(move.toString());
                            y = position2;
                            System.out.println("moved");
                            turn+=1;
                            return true;
                        }
                        System.out.println("cannot move to the spot");
                        return false;
                    }
                    System.out.println("cannot move to the spot");
                    return false;
                }
            }
            if (Math.abs(x-position1)==1 && y - position2 == 1){
                if (kill(position1,position2,move)){
                    turn+=1;
                    return true;
                }
                return false;
            }
            if (x == position1 && y - position2 == 1){
                if (Table.isInThisHomeAnyPiece(x,position2)==null){
                    move.append(" "+String.valueOf(x)+","+String.valueOf(y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)) ;
                    Players.allmoves.add(move.toString());
                    y= position2;
                    System.out.println("moved");
                    turn+=1;
                    return true;
                }
                System.out.println("cannot move to the spot");
                return false;
            }
        }
        return false;
    }

    @Override
    protected boolean kill(int position1, int position2,StringBuilder move) {
       if (super.kill(position1,position2,move))
           return true;
       return false;
    }

    @Override
    protected boolean undo(int position1, int position2) {
        return super.undo(position1, position2);
    }
}
