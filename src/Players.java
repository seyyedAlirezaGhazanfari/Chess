import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Map;

public class Players {
    private Participator participator;
    private boolean isItWhite;
    private int turn;
    private int undo;
    private ArrayList<String> movments;
    private ArrayList<Pieces> allPieces;
    public static ArrayList<String > allmoves;
    public Players(Participator participator, boolean isItWhite, int turn) {
        this.participator = participator;
        this.isItWhite = isItWhite;
        this.turn = turn;
        allPieces = new ArrayList<>();
        movments = new ArrayList<>();
        undo = 2;
    }

    public ArrayList<String> getMovments() {
        return movments;
    }

    public void setMovments(ArrayList<String> movments) {
        this.movments = movments;
    }

    public Participator getParticipator() {
        return participator;
    }

    public void setItWhite(boolean itWhite) {
        isItWhite = itWhite;
    }

    public boolean isItWhite() {
        return isItWhite;
    }

    public int getUndo() {
        return undo;
    }

    public void setUndo(int undo) {
        this.undo = undo;
    }

    public ArrayList<Pieces> getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(ArrayList<Pieces> allPieces) {
        this.allPieces = allPieces;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}