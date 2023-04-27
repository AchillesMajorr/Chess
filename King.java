package edu.siena.csis225.notchess;
public class King extends ChessPiece {
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        int deltaX = Math.abs(toX - getX());
        int deltaY = Math.abs(toY - getY());

        // King can only move one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        // Check if destination is not occupied by a piece of the same color
        if (board[toX][toY] != null && board[toX][toY].isWhite() == isWhite()) {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
