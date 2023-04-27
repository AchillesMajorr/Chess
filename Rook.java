package edu.siena.csis225.notchess;

public class Rook extends ChessPiece {
   
    public Rook(int x, int y, boolean isWhite) {
        super(x,y,isWhite);
        

    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        int deltaX = toX - getX();
        int deltaY = toY - getY();

        // Ensure the rook moves either horizontally or vertically
        if (deltaX != 0 && deltaY != 0) {
            return false;
        }

        // Check if there are any pieces blocking the rook's path
        if (deltaX == 0) {
            int startY = Math.min(getY(), toY) + 1;
            int endY = Math.max(getY(), toY);
            for (int i = startY; i < endY; i++) {
                if (board[toX][i] != null) {
                    return false;
                }
            }
        } else {
            int startX = Math.min(getX(), toX) + 1;
            int endX = Math.max(getX(), toX);
            for (int i = startX; i < endX; i++) {
                if (board[i][toY] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

