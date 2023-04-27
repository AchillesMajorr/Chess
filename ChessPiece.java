package edu.siena.csis225.notchess;
/**
 * base class for all chess pieces.
 */
public abstract class ChessPiece {
    public int x;
    public int y;
    public boolean isWhite;

    public ChessPiece(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public abstract boolean canMove(int x, int y, ChessPiece[][] board);

    // helper method to check if a move is within the board bounds
    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, ChessPiece[][] board) {
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
    public abstract String getSymbol();
}
