package edu.siena.csis225.notchess;

/** 
 * Should be used to display the board, manage turns, win conditions, draws,
 * when the game is over, and when a capture must be taken.
 */
public class GameState {
    public static final GameState ACTIVE = null;
    public ChessPiece[][] board;
    public boolean  whiteTurn;
    public int turnCount;
    public static final GameState WHITE_WIN = new GameState();
    public static final GameState BLACK_WIN = new GameState();
    public static final GameState DRAW = new GameState();

    public GameState() {
        board = new ChessPiece[8][8];
        whiteTurn = true;
        turnCount = 0;
    }

    /**
     * Might not need this.
     */
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 
     * @return
     */
    public boolean isCaptureAvailable() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null && piece.isWhite() == whiteTurn) {
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board[0].length; l++) {
                            if (piece.isValidMove(i, j, k, l, board)) {
                                ChessPiece destPiece = board[k][l];
                                if (destPiece != null && destPiece.isWhite() != whiteTurn) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    

    /**
     * Is the game over?
     * @return
     */
    public boolean isGameOver() {
        if (turnCount >= 50) {
            System.out.println("Draw by fifty-move rule.");
            return true;
        }
    
        int whiteCount = 0;
        int blackCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null) {
                    if (piece.isWhite()) {
                        whiteCount++;
                    } else {
                        blackCount++;
                    }
                }
            }
        }
    
        if (whiteCount == 0) {
            System.out.println("White wins!");
            return true;
        } else if (blackCount == 0) {
            System.out.println("Black wins!");
            return true;
        }
    
        if (!isCaptureAvailable()) {
            System.out.println("Draw by stalemate.");
            return true;
        }
    
        return false;
    }
    /**
     * Should keep track of any moves made by a player.
     * @param fromRow
     * @param fromCol
     * @param toRow
     * @param toCol
     * @return
     */
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        ChessPiece piece = board[fromRow][fromCol];
        if (piece == null || piece.isWhite() != whiteTurn) {
            return false;
        }
        if (!piece.isValidMove(fromRow, fromCol, toRow, toCol, board)) {
            return false;
        }
        ChessPiece destPiece = board[toRow][toCol];
        if (destPiece != null && destPiece.isWhite() == whiteTurn) {
            return false;
        }
        board[fromRow][fromCol] = null;
        board[toRow][toCol] = piece;
        piece.setX(toRow);
        piece.setY(toCol);
        // Check if the move resulted in a capture
        if (destPiece != null) {
            // Remove the captured piece from the board
            board[toRow][toCol] = null;
        // Check if the player has lost all their pieces
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ChessPiece p = board[i][j];
                if (p != null && p.isWhite() == whiteTurn) {
                    count++;
                }
            }
        }
        if (count == 0) 
        //Check if the game is over after the move. 
        isGameOver();
            return true;
        }
        
        whiteTurn = !whiteTurn;
        turnCount++;
        return true;
    }
}    
