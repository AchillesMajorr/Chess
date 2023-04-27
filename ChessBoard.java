package edu.siena.csis225.notchess;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JPanel;

public class ChessBoard extends JPanel {
    
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public ChessPiece[][] board;

    
    
        public ChessBoard() {
            this.board = new ChessPiece[ROWS][COLS];
    
            // Add pawns to the board
            for (int col = 0; col < COLS; col++) {
                board[1][col] = new Pawn(1, col, false);
                board[6][col] = new Pawn(6, col, true);
            }
    
            // Add rooks to the board
            board[0][0] = new Rook(0, 0, false);
            board[0][7] = new Rook(0, 7, false);
            board[7][0] = new Rook(7, 0, true);
            board[7][7] = new Rook(7, 7, true);
    
            // Add knights to the board
            board[0][1] = new Knight(0, 1, false);
            board[0][6] = new Knight(0, 6, false);
            board[7][1] = new Knight(7, 1, true);
            board[7][6] = new Knight(7, 6, true);
    
            // Add bishops to the board
            board[0][2] = new Bishop(0, 2, false);
            board[0][5] = new Bishop(0, 5, false);
            board[7][2] = new Bishop(7, 2, true);
            board[7][5] = new Bishop(7, 5, true);
    
            // Add kings and queens to the board
            board[0][4] = new King(0, 4, false);
            board[0][3] = new Queen(0, 3, false);
            board[7][4] = new King(7, 4, true);
            board[7][3] = new Queen(7, 3, true);
        }
    

        //Get a piece from a certain row and col
        public ChessPiece getPiece(int row, int col) {
            if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
                return null;
            }
            return board[row][col];
        }
    
        //Move a piece to a certain row and col then update it's position.
        public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
            ChessPiece piece = board[fromRow][fromCol];
            board[fromRow][fromCol] = null;
            board[toRow][toCol] = piece;
            piece.setX(toRow);
            piece.setY(toCol);
        }
    
        //Might not need this
        public int getRows(){
            return ROWS;

        }
        //Might Not need this.
        public int getColumns(){
            return COLS;
        }
    
        //Set a chesspiece object to a certain position on the board, might be redundent, since 
        //we have movePiece. 
    public void setPiece(int row, int col, ChessPiece piece) {
        board[row][col] = piece;
    }

    //Might not need this as well. since movePiece already updates position after a move. 
    public void updateBoard(ChessPiece piece, int newRow, int newCol) {
    int oldRow = piece.getX();
    int oldCol = piece.getY();
    board[oldRow][oldCol] = null;
    board[newRow][newCol] = piece;
    piece.setX(newRow);
    piece.setY(newCol);
}
//Should print current state of the board. 
public void print() {
    System.out.println(board);
    
}

public Move getMoveFromUserInput(Player player) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter move for " + player + " player (e.g. a2 a4): ");
    String input = scanner.nextLine().trim();
    String[] moveCoords = input.split(" ");
    int fromRow = Integer.parseInt(moveCoords[0].substring(1)) - 1;
    int fromCol = moveCoords[0].charAt(0) - 'a';
    int toRow = Integer.parseInt(moveCoords[1].substring(1)) - 1;
    int toCol = moveCoords[1].charAt(0) - 'a';

    Point startPos = new Point(fromCol, fromRow);
    Point endPos = new Point(toCol, toRow);
    return new Move(startPos, endPos);
}

public boolean isMoveValid(int fromRow, int fromCol, int toRow, int toCol, ChessPiece[][] board) {
    ChessPiece piece = board[fromRow][fromCol];
    ChessPiece destPiece = board[toRow][toCol];

    // Check if the piece being moved can move to the destination square according to its movement rules
    if (!piece.canMove(toRow, toCol, board)) {
        return false;
    }

    // Check if the destination square is not occupied by a piece of the same color
    if (destPiece != null && destPiece.isWhite() == piece.isWhite()) {
        return false;
    }

    // All conditions are met, so the move is valid
    return true;
}


    

public ChessPiece getPieceAt(int row, int col) {
    return board[row][col];
}







}
