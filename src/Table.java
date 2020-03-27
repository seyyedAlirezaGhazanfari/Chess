public class Table {
    private static Players player1;
    private static Players players2;

    public Table(Players player1, Players players2) {
        this.player1 = player1;
        this.players2 = players2;
    }

    public void makeTable(){
        for (int i = 1;i<=8;i++){
            if (i == 2){
                for (int j = 1;j<=8;j++){
                    Pawn soldier = new Pawn(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Pawn","Pw",PieceFormatView.FUTURE);
                    player1.getAllPieces().add(soldier);
                }
            }
            if (i == 1){
                for (int j = 1 ; j<=8;j++){
                    if (j==8 || j == 1){
                        Rook rook = new Rook(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Rook","Rw",PieceFormatView.FUTURE);
                        player1.getAllPieces().add(rook);
                    }
                    if (j==2 || j==7){
                        Knight horse = new Knight(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Knight","Nw",PieceFormatView.FUTURE);
                        player1.getAllPieces().add(horse);
                    }
                    if (j==3 || j==6){
                        Bishop elephant = new Bishop(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Bishop","Bw",PieceFormatView.FUTURE);
                        player1.getAllPieces().add(elephant);
                    }
                    if (j==4){
                        Queen counselor = new Queen(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Queen","Qw",PieceFormatView.FUTURE);
                        player1.getAllPieces().add(counselor);
                    }
                    if (j == 5){
                        King king = new King(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"King","Kw",PieceFormatView.FUTURE);
                        player1.getAllPieces().add(king);
                    }
                }
            }
            if (i==7){
                for (int j = 1;j<=8;j++){
                    Pawn soldier = new Pawn(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,false,"Pawn","Pb",PieceFormatView.FUTURE);
                    players2.getAllPieces().add(soldier);
                }
            }
            if (i==8){
                for (int j = 1 ; j<=8;j++){
                    if (j==8 || j == 1){
                        Rook rook = new Rook(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Rook","Rb",PieceFormatView.FUTURE);
                        players2.getAllPieces().add(rook);
                    }
                    if (j==2 || j==7){
                        Knight horse = new Knight(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Knight","Nb",PieceFormatView.FUTURE);
                        players2.getAllPieces().add(horse);
                    }
                    if (j==3 || j==6){
                        Bishop elephant = new Bishop(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Bishop","Bb",PieceFormatView.FUTURE);
                        players2.getAllPieces().add(elephant);
                    }
                    if (j==4){
                        Queen counselor = new Queen(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"Queen","Qb",PieceFormatView.FUTURE);
                        players2.getAllPieces().add(counselor);
                    }
                    if (j == 5){
                        King king = new King(i,j,PeiceForm.DESELECTED,PieceAttention.ALIVE,true,"King","Kb",PieceFormatView.FUTURE);
                        players2.getAllPieces().add(king);
                    }
                }
            }
        }
    }
    public static Pieces isInThisHomeAnyPiece(int x , int y){
            for (Pieces piece : player1.getAllPieces()) {
                if (piece.x == x && piece.y == y)
                    return piece;
            }
        for (Pieces piece : players2.getAllPieces()) {
            if (piece.x==x && piece.y==y)
                return piece;
        }
        return null;
    }
    public void PrintTable(){
        for (int i =1;i<=8;i++){
            for (int j = 1;j<=8;j++){
                if (Table.isInThisHomeAnyPiece(i,j)==null){
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
        }
    }
}
