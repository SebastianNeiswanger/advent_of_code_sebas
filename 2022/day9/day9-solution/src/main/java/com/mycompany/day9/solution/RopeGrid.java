package com.mycompany.day9.solution;

/**
 * This class will hold the information about your rope.
 * The head will move according to the instructions and
 * the tail will follow along.
 */
public class RopeGrid {
    private int GRID_SIZE = 1000;
    private char[][] grid;
    private int[] head = new int[2];
    private int[][] tail;

    /**
     * This will create a new grid to hold your rope,
     * and initialize the Grid to size 1000x1000
     * with the rope starting in the center. It 
     * will initialize with only 1 knot in the tail.
     */
    public RopeGrid() {
        grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
        // Set start for head and tail
        head[0] = GRID_SIZE/2;
        head[1] = GRID_SIZE/2;
        tail = new int[1][2];
        tail[0][0] = GRID_SIZE/2;
        tail[0][1] = GRID_SIZE/2;
        grid[GRID_SIZE/2][GRID_SIZE/2] = '*';
    }

    /**
     * This will allow custom lengths to the grid and rope. Knots will increase the
     * rope size. The grid will be gridSize*gridSize
     * 
     * @param gridSize The total size you would like to make your grid
     * @param tailKnots The number of knots you would like in the tail
     * of your rope. The tail is still counted as the very end of the rope.
     */
    public RopeGrid(int gridSize, int tailKnots) {
        GRID_SIZE = gridSize;
        grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
        // Set start for head and tail
        head[0] = GRID_SIZE/2;
        head[1] = GRID_SIZE/2;
        tail = new int[tailKnots][2];
        for (int i = 0; i < tailKnots; i++) {
            tail[i][0] = GRID_SIZE/2;
            tail[i][1] = GRID_SIZE/2;
        }
        grid[GRID_SIZE/2][GRID_SIZE/2] = '*';
    }

    /**
     * This will move the head of the rope based on direction and distance.
     * It will also move the tail of the rope right behind the head.
     * 
     * @param direction Either "N" North, "S" South, "E" East, "W" West
     * @param distance Any number for how far the head needs to move
     */
    public void moveHead(String direction, int distance) {
        if (direction.equals("U")) {
            for (int i = 0; i < distance; i++) {
                // move the head up one at a time
                head[1] -= 1;
                moveTail();
            }
        } else if (direction.equals("D")) {
            for (int i = 0; i < distance; i++) {
                // move the head down one at a time
                head[1] += 1;
                moveTail();
            }
        } else if (direction.equals("L")) {
            for (int i = 0; i < distance; i++) {
                // move the head left one at a time
                head[0] -= 1;
                moveTail();
            }
        } else if (direction.equals("R")) {
            for (int i = 0; i < distance; i++) {
                // move the head right one at a time
                head[0] += 1;
                moveTail();
            }
        }
    }

    /**
     * This method moves the tail (every knot behind the head)
     * to follow the head. If there is more than one space extra
     * between one knot and the previous knot, the current knot should
     * move. The last knot in the tail will mark it's position on the grid.
     */
    private void moveTail() {
        int ropeSize = tail.length;
        for (int i = 0; i < ropeSize; i++) {
            int xDiference;
            int yDiference;
            if (i == 0) {
                // First knot needs to follow the head
                xDiference = head[0] - tail[i][0];
                yDiference = head[1] - tail[i][1];
            } else {
                // Every other knot follows the previous knot
                xDiference = tail[i-1][0] - tail[i][0];
                yDiference = tail[i-1][1] - tail[i][1];
            }
            // Move Tail to follow next knot
            if ((xDiference <= -1 && yDiference == -2) || (xDiference == -2 && yDiference <= -1)) {
                // NW Diagonal
                tail[i][0] -= 1;
                tail[i][1] -= 1;
            } else if ((xDiference >= 1 && yDiference == -2) || (xDiference == 2 && yDiference <= -1)) {
                // NE Diagonal
                tail[i][0] += 1;
                tail[i][1] -= 1;
            } else if ((xDiference <= -1 && yDiference == 2) || (xDiference == -2 && yDiference >= 1)) {
                // SW Diagonal
                tail[i][0] -= 1;
                tail[i][1] += 1;
            } else if ((xDiference >= 1 && yDiference == 2) || (xDiference == 2 && yDiference >= 1)) {
                // SE Diagonal
                tail[i][0] += 1;
                tail[i][1] += 1;
            } else if (yDiference == -2) {
                // North
                tail[i][1] -= 1;
            } else if (yDiference == 2) {
                // South
                tail[i][1] += 1;
            } else if (xDiference == 2) {
                // East
                tail[i][0] += 1;
            } else if (xDiference == -2) {
                // West
                tail[i][0] -= 1;
            }
        }
        // Let the grid know where the end of the tail visited
        grid[tail[ropeSize-1][0]][tail[ropeSize-1][1]] = '*';
    }

    /**
     * This will walk through the entire grid to figure out how many spaces
     * the tail of the rope visited
     * 
     * @return An integer representation of how many spaces the tail of the rope visited
     */
    public int visitedTailSpaces() {
        int visitedSpaces = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // '*' is a position the end of the tail visited
                if (grid[i][j] == '*') { visitedSpaces++; }
            }
        }
        return visitedSpaces;
    }
}
