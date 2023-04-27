package edu.siena.csis225.notchess;

public class Knight extends ChessPiece {
    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        int deltaX = Math.abs(toX - getX());
        int deltaY = Math.abs(toY - getY());
        if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            // Check if destination is empty or occupied by an opponent piece
            if (board[toX][toY] == null || board[toX][toY].isWhite() != isWhite()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "KN";
    }
}
