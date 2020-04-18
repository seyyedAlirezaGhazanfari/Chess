import java.util.ArrayList;


public class Players {
    private Participator participator;

    private int undo;
    private ArrayList<String> movments;
    private ArrayList<Pieces> allPieces;
    public static ArrayList<String> allKilledPiecesNickNamesOfPlayerWhite;
    public static ArrayList<String> allKilledPiecesNickNamesOfPlayerBlack;
    public static ArrayList<String> allKilledPerson = new ArrayList<>();

    public Players(Participator participator) {
        this.participator = participator;
        allPieces = new ArrayList<>();
        movments = new ArrayList<>();
        undo = 2;
    }

    public ArrayList<String> getMovements() {
        return movments;
    }


    public Participator getParticipator() {
        return participator;
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
}
