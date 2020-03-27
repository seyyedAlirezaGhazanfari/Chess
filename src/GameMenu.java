import java.lang.reflect.Type;
import java.util.ArrayList;

public class GameMenu {
     private static Players players1;
     private static Players player2;
     private static int turn;
     private static boolean isThereAMoveInThisTurn;
     private static boolean isThereAUndoInThisTurn;
     public static boolean isThereAKillInThisTurn;
     private static String moveSaver;
     private static String moveSubmission;


     public GameMenu(Participator participator, Participator participator2) {
          players1 = new Players(participator,true,1);
          player2 = new Players(participator2,false,1);
          turn = 1;
          Table table = new Table(players1,player2);
          table.makeTable();
          moveSaver = new String();
          Players.allmoves = new ArrayList<>();
     }
     public static boolean select(int x , int y){
          if (x>8||y>8||x<1||y<1){
               System.out.println("wrong coordination");
               return false;
          }
          if (turn%2!=0){
               disSelectAllPieces(players1);
               for (Pieces piece : players1.getAllPieces()) {
                    if (piece.x==x && piece.y == y){
                         piece.peiceForm = PeiceForm.SELECTED;
                         System.out.println("selected");
                         return true;
                    }
               }
               if (Table.isInThisHomeAnyPiece(x,y)!=null){
                    System.out.println("you can only select one of your pieces");
                    return false;
               }
               if (Table.isInThisHomeAnyPiece(x,y)==null){
                    System.out.println("no piece on this spot");
                    return false;
               }
          }
          else {
               disSelectAllPieces(player2);
               for (Pieces piece : player2.getAllPieces()) {
                    if (piece.x==x && piece.y==y){
                         piece.peiceForm = PeiceForm.SELECTED;
                         System.out.println("selected");
                         return true;
                    }
               }
               if (Table.isInThisHomeAnyPiece(x,y)!=null){
                    System.out.println("you can only select one of your pieces\n");
                    return false;
               }
               if (Table.isInThisHomeAnyPiece(x,y)==null){
                    System.out.println("no piece on this spot");
                    return false;
               }

          }
          return false;
     }
     public static boolean deslect(){
          if (turn%2!=0) {
               for (Pieces piece : players1.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED)) {
                         piece.peiceForm = PeiceForm.DESELECTED;
                         System.out.println("deselected");
                         return true;
                    }
               }
          }
          else {
               for (Pieces piece : player2.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED)) {
                         piece.peiceForm = PeiceForm.DESELECTED;
                         System.out.println("deselected");
                         return true;
                    }
               }
          }
          System.out.println("no piece is selected");
          return false;
     }
     public static boolean move(int x , int y){
          StringBuilder move = new StringBuilder();
          moveSaver = new String();
          moveSaver = moveSaver + String.valueOf(x) + ":"+String.valueOf(y)+":";
          if (isThereAMoveInThisTurn==true){
               System.out.println("already moved");
               return false;
          }
          if (x>8||x<1||y<1||y>8){
               System.out.println("wrong coordination");
               return false;
          }
          if (turn%2!=0){
               for (Pieces piece : players1.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED)){
                         moveSaver +=String.valueOf(piece.x)+":"+String.valueOf(piece.y);
                         if (pieceTypeChoose(x,y,piece,move)){
                              isThereAMoveInThisTurn = true;
                              players1.getMovments().add(move.toString());
                              moveSubmission = move.toString();
                              return true;
                         }
                         return false;
                    }
               }
          }
          else {
               for (Pieces piece : player2.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED)){
                         moveSaver +=String.valueOf(piece.x)+":"+String.valueOf(piece.y);
                         if (pieceTypeChoose(x,y,piece,move)){
                              isThereAMoveInThisTurn=true;
                              player2.getMovments().add(move.toString());
                              moveSubmission = move.toString();
                              return true;
                         }
                         return false;
                    }

               }
          }
          System.out.println("do not have any selected piece");
          return false;
     }
     public static boolean next_turn(){
          if (!isThereAMoveInThisTurn) {
               System.out.println("you must move then proceed to next turn");
               return false;
          }
          isThereAMoveInThisTurn =false;
          isThereAUndoInThisTurn=false;
          if (turn%2!=0){
               for (Pieces piece : players1.getAllPieces()) {
                    if (piece.pieceFormatView.equals(PieceFormatView.NOW)) {
                         piece.pieceFormatView = PieceFormatView.PAST;
                         piece.x=0;
                         piece.y=0;
                    }
               }
          }
          else {
               for (Pieces piece : player2.getAllPieces()) {
                    if (piece.pieceFormatView.equals(PieceFormatView.NOW)) {
                         piece.pieceFormatView = PieceFormatView.PAST;
                         piece.x=0;
                         piece.y=0;
                    }
               }
          }
          turn+=1;
          System.out.println("turn completed");
          return true;
     }
     public static boolean showTurn(){
          String username;
          String color;
          if (turn%2==0){
               username = player2.getParticipator().getUsername();
               color="black";
          }
          else {
               username = players1.getParticipator().getUsername();
               color = "white";
          }
          System.out.println("it is player "+username+" turn with color "+color);
          return true;
     }
     public static boolean undo(){
          if (turn%2==0){
               if (player2.getUndo()==0){
                    System.out.println("you cannot undo anymore");
                    return false;
               }
               if (!isThereAMoveInThisTurn){
                    System.out.println("you must move before undo");
                    return false;
               }
               if (isThereAUndoInThisTurn){
                    System.out.println("you have used your undo for this turn");
                    return false;
               }
               player2.setUndo(player2.getUndo()-1);
              player2.getMovments().remove(moveSubmission);
          }
          else {
                    if (players1.getUndo()==0){
                         System.out.println("you cannot undo anymore");
                         return false;
                    }
               if (!isThereAMoveInThisTurn){
                    System.out.println("you must move before undo");
                    return false;
               }
               if (isThereAUndoInThisTurn){
                    System.out.println("you have used your undo for this turn");
                    return false;
               }
               players1.setUndo(players1.getUndo()-1);
            //   players1.getMovments().remove(move.toString());
          }
          System.out.println("undo completed");
          String[] moveSaverSplits = moveSaver.split(":");
          undoProcess(Integer.parseInt(moveSaverSplits[2]),Integer.parseInt(moveSaverSplits[3]),showSelected());
          isThereAUndoInThisTurn=true;
          isThereAMoveInThisTurn=false;
          return true;
     }
     public static Pieces showSelected(){
          if (turn%2==0){
               for (Pieces piece : player2.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED))
                         return piece;
               }
          }
          else {
               for (Pieces piece : players1.getAllPieces()) {
                    if (piece.peiceForm.equals(PeiceForm.SELECTED))
                         return piece;
               }
          }
          return null;
     }
     public static boolean showUndoNumber(){
          if (turn%2!=0){
               System.out.println("you have "+players1.getUndo()+" undo moves");
          }
          else {
               System.out.println("you have "+player2.getUndo()+" undo moves");
          }
          return true;
     }
     public static boolean showMovesOfThisPlayer(){
          if (turn%2!=0){
               for (String movment : players1.getMovments()) {
                    System.out.println(movment);
               }
               return true;
          }
          else {
               for (String movment : player2.getMovments()) {
                    System.out.println(movment);
               }
               return true;
          }
     }
     public static boolean showMoveAll(){
          for (String s : Players.allmoves) {
               System.out.println(s);
          }
          return true;
     }
     public static boolean showKilledPieceOfThisPlayer(){
          return true;
     }
     public static boolean showKilledAll(){
          return true;
     }
     public static void help(){
         System.out.println("select [x],[y]\ndeselect\nmove [x],[y]\nnext_turn\nshow_turn\nundo\nundo_number\nshow_moves [-all]\nshow_killed [-all]\nshow_board\nhelp\nforfeit");
     }
     public static void disSelectAllPieces(Players player){
          for (Pieces piece : player.getAllPieces()) {
               if (piece.peiceForm.equals(PeiceForm.SELECTED)){
                    piece.peiceForm = PeiceForm.DESELECTED;
               }
          }
     }
     public static boolean pieceTypeChoose(int x, int y, Pieces piece,StringBuilder move) {
          if (piece.peiceForm.equals(PeiceForm.SELECTED)){
               switch (piece.type){
                    case "Pawn":
                         Pawn pawn = (Pawn) piece;
                         move.append(pawn.nickName);
                       return   pawn.move(x,y,move);
                    case "Rook":
                         Rook rook = (Rook) piece;
                         move.append(rook.nickName);
                       return   rook.move(x,y,move);
                    case "Queen":
                         Queen queen = (Queen) piece;
                         move.append(queen.nickName);
                       return   queen.move(x,y,move);
                    case "King":
                         King king = (King) piece;
                         move.append(king.nickName);
                       return   king.move(x,y,move);
                    case "Knight":
                         Knight knight = (Knight) piece;
                         move.append(knight.nickName);
                      return    knight.move(x,y,move);
                    case "Bishop":
                         Bishop bishop = (Bishop) piece;
                         move.append(bishop.nickName);
                        return bishop.move(x,y,move);
               }
          }
          return false;
     }
     public static boolean undoProcess(int x , int y,Pieces piece){
          if (piece.peiceForm.equals(PeiceForm.SELECTED)){
               switch (piece.type){
                    case "Pawn":
                         Pawn pawn = (Pawn) piece;
                         pawn.turn-=1;
                         return   pawn.undo(x,y);
                    case "Rook":
                         Rook rook = (Rook) piece;
                         return   rook.undo(x,y);
                    case "Queen":
                         Queen queen = (Queen) piece;
                         return   queen.undo(x,y);
                    case "King":
                         King king = (King) piece;
                         return   king.undo(x,y);
                    case "Knight":
                         Knight knight = (Knight) piece;
                         return    knight.undo(x,y);
                    case "Bishop":
                         Bishop bishop = (Bishop) piece;
                         return bishop.undo(x,y);
               }
          }
          return false;
     }
}
