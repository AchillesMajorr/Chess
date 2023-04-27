package edu.siena.csis225.notchess;
import java.awt.Point;
import java.util.Scanner;

public class Move {
    private final Point startPos;
    private final Point endPos;

    public Move(Point startPos, Point endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public Point getStartPos() {
        return startPos;
    }

    public Point getEndPos() {
        return endPos;
    }

    
}


