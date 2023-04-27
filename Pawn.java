package edu.siena.csis225.notchess;


public class Pawn extends ChessPiece {
    private boolean hasMoved;

    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.hasMoved = false;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        int deltaX = toX - getX();
        int deltaY = toY - getY();

        // Ensure the pawn moves only one row at a time
        if (Math.abs(deltaX) != 1 && Math.abs(deltaX) != 2) {
            return false;
        }

        // Ensure the pawn moves forward
        if ((isWhite() && deltaY <= 0) || (!isWhite() && deltaY >= 0)) {
            return false;
        }

        // Ensure the pawn moves straight ahead
        if (deltaY != -1 && deltaY != -2) {
            return false;
        }

        // Ensure the pawn doesn't move forward if there is a piece blocking it
        int direction = isWhite() ? -1 : 1;
        int newX = getX() + direction;
        int newY = getY();
        if (board[newX][newY] != null) {
            return false;
        }

        // If the pawn moves two rows at once, ensure it hasn't moved before and that there are no pieces in front of it
        if (Math.abs(deltaY) == 2) {
            if (hasMoved || board[newX + direction][newY] != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}

