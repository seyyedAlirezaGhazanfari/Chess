import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Pieces {
    public static ArrayList <Pieces> allPieces =  new ArrayList<>();
    protected int x;
    protected int y;
    protected int turn;
    protected String type;
    protected boolean isPeiceWhite;
    protected PeiceForm peiceForm;
    protected PieceAttention pieceAttention;
    protected String nickName;
    protected PieceFormatView pieceFormatView;

    public Pieces(int y, int x, PeiceForm peiceForm, PieceAttention pieceAttention, boolean isPeiceWhite,String type,String nickName,PieceFormatView pieceFormatView) {
        this.x = x;
        this.y = y;
        this.peiceForm = PeiceForm.DESELECTED;
        this.pieceAttention = PieceAttention.ALIVE;
        this.pieceFormatView = PieceFormatView.FUTURE;
        allPieces.add(this);
        this.isPeiceWhite = isPeiceWhite;
        this.type = type;
        this.nickName = nickName;
        turn=1;
    }


    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public String getNickName() {
        return nickName;
    }

    protected boolean move(int position1 , int position2,StringBuilder move){
        return true;
    }
    protected boolean kill(int position1 , int position2,StringBuilder move){
        if (Table.isInThisHomeAnyPiece(position1,position2)==null){
            return false;
        }
        for (Pieces piece : allPieces) {
            if (piece.x==position1 && piece.y==position2){
                if (piece.isPeiceWhite == isPeiceWhite){
                    return false;
                }
                else {
                    piece.pieceFormatView = PieceFormatView.NOW;
                    piece.pieceAttention = PieceAttention.KILLED;
                    move.append(" "+String.valueOf(piece.x)+","+String.valueOf(piece.y)+" to "+String.valueOf(position1)+","+String.valueOf(position2)+" destroyed "+piece.nickName);
                    Players.allmoves.add(move.toString());
                    x=position1;
                    y = position2;
                    System.out.println("rival piece destroyed");
                    GameMenu.isThereAKillInThisTurn = true;
                    return true;
                }
            }
        }
        return false;
    }
    public static Pieces findPiecesByPosition(int x , int y){
        for (Pieces piece : allPieces) {
            if (piece.x==x && piece.y==y)
                return piece;
        }
        return null;
    }
    protected boolean undo(int position1 , int position2){
        for (String s : Players.allmoves) {
            if (s.equals(nickName+" "+String.valueOf(position1)+","+String.valueOf(position2)+" to "+String.valueOf(x)+","+String.valueOf(y))){
                allPieces.remove(s);
                break;
            }
        }
       setX(position1);
       setY(position2);
        return true;
    }
}

