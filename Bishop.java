package edu.siena.csis225.notchess;

public class Bishop extends ChessPiece {

    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean canMove(int toX, int toY, ChessPiece[][] board) {
        int deltaX = toX - getX();
        int deltaY = toY - getY();

        // Ensure the bishop moves diagonally
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            return false;
        }

        // Check if the path is blocked
        int xDir = deltaX > 0 ? 1 : -1;
        int yDir = deltaY > 0 ? 1 : -1;

        int currX = getX() + xDir;
        int currY = getY() + yDir;
        while (currX != toX && currY != toY) {
            if (board[currX][currY] != null) {
                return false;
            }
            currX += xDir;
            currY += yDir;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, ChessPiece[][] board) {
        // check if destination is outside board
        if (toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7) {
            return false;
        }
        
        // check if destination is occupied by piece of same color
        if (board[toRow][toCol] != null && board[toRow][toCol].isWhite() == isWhite()) {
            return false;
        }
        
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // bishop can only move diagonally
        if (rowDiff != colDiff) {
            return false;
        }
        
        // check if path to destination is clear
        int rowStep = (toRow - fromRow) > 0 ? 1 : -1;
        int colStep = (toCol - fromCol) > 0 ? 1 : -1;
        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;
        
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        return true;
    }
}    
