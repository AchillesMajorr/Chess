package edu.siena.csis225.notchess;

public class Queen extends ChessPiece {
    
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        // Check if move is diagonal, horizontal or vertical
        if (Math.abs(toX - getX()) == Math.abs(toY - getY()) ||
                toX == getX() || toY == getY()) {
            // Check if the path to the destination is clear
            if (isPathClear(getX(), getY(), toX, toY, board)) {
                // Check if the destination is occupied by a piece of the opposite color or empty
                return board[toX][toY] == null || board[toX][toY].isWhite() != isWhite();
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    // Helper method to check if the path from (fromX, fromY) to (toX, toY) is clear
    private boolean isPathClear(int fromX, int fromY, int toX, int toY, ChessPiece[][] board) {
        int deltaX = Integer.signum(toX - fromX);
        int deltaY = Integer.signum(toY - fromY);
        int x = fromX + deltaX;
        int y = fromY + deltaY;
        while (x != toX || y != toY) {
            if (board[x][y] != null) {
                return false;
            }
            x += deltaX;
            y += deltaY;
        }
        return true;
    }
}
