package edu.siena.csis225.notchess;
import java.util.Scanner;
/** 
 * Should be used to start a game.
 */

 import java.util.Scanner;

 public class ChessGame {
     public ChessBoard board;
     public GameState state;
     public Player currentPlayer;
     public int turnsLeft;
     

 
     public ChessGame() {
         this.board = new ChessBoard();
         this.state = GameState.ACTIVE;
         this.currentPlayer = Player.WHITE;
         this.turnsLeft = 50;
     }
 
     public void start() {
         Scanner scanner = new Scanner(System.in);
         System.out.println("Welcome to NotChess! The goal is to lose all your pieces.");
         System.out.println("Type H to play Human vs Human mode or type C to play against the computer: ");
         String mode = scanner.nextLine().trim();
 
         if (mode.equalsIgnoreCase("H")) {
             playHumanVsHuman();
         } else if (mode.equalsIgnoreCase("C")) {
             playHumanVsComputer();
         } else {
             System.out.println("Invalid input. Exiting game.");
             System.exit(0);
         }
     }
     
 
     private void playHumanVsHuman() {
        //While the game state is active. 
         while (state == GameState.ACTIVE) {
            //Print the board.
             board.print();

             System.out.println("Turns left: " + turnsLeft);
             System.out.println(currentPlayer + " player's turn.");
 
             Move move = null;

            while (move == null) {
                //get move from current player
            move = board.getMoveFromUserInput(currentPlayer);

            if (!board.isMoveValid((int) move.getStartPos().getX(), (int) move.getStartPos().getY(),
            (int) move.getEndPos().getX(), (int) move.getEndPos().getY(), board)) {
             System.out.println("Invalid move. Please try again.");
            move = null;
    }
}

 
             board.makeMove(move);
             if (board.hasPlayerLost(currentPlayer)) {
                 state = (currentPlayer == Player.WHITE) ? GameState.BLACK_WIN : GameState.WHITE_WIN;
             } else if (--turnsLeft == 0) {
                 state = GameState.DRAW;
             } else {
                 currentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
             }
         }
 
         System.out.println("Game over.");
         if (state == GameState.WHITE_WIN) {
             System.out.println("White player wins!");
         } else if (state == GameState.BLACK_WIN) {
             System.out.println("Black player wins!");
         } else {
             System.out.println("It's a draw!");
         }
     }
 
     private void playHumanVsComputer() {
         // Implement human vs computer mode
     }
 
     public static void main(String[] args) {
         ChessGame game = new ChessGame();
         game.start();
     }
 }
 

