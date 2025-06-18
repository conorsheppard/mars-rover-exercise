package com.conorsheppard.exercise;

import lombok.Data;

import static com.conorsheppard.exercise.Direction.*;

@Data
public class MarsRover {
    private int[] position;
    private Direction direction;
//    private Board board;
    private int[][] board;
    //[0, 0, 0, 0]
    //[0, 0, 0, 0]
    //[0, 1, 0, 0]
    //[1, 0, 0, 0]

    // Board = m x n 2D array int[][]{}
    // map obstacles


    public MarsRover(int[] position, Direction direction, char[][] board) {
        this.position = position;
        this.direction = direction;
        this.board = board;
    }

    public void executeCommand(String command) {
        switch (command) {
            case "L" -> rotateLeft();
            case "R" -> rotateRight();
            case "M" -> move();
        }
    }

    private void move() {
        int nextX = position[0];
        int nextY = position[1];
        switch (direction) {
            case N -> nextY++;
            case S -> nextY--;
            case E -> nextX++;
            case W -> nextX--;
        }

        if (isInBounds(nextX, nextY)
//                && isObstacle()
        ) {
                position[0] = nextX;
                position[1] = nextY;
        } else {
            throw new RuntimeException("Rover has fallen off the board");
        }
    }

    private boolean isObstacle(int x, int y) {
        // check the 2D char array at the given coordinates
    }

    void rotateLeft() {
        switch (direction) {
            case N -> direction = W;
            case S -> direction = E;
            case E -> direction = N;
            case W -> direction = S;
        }
    }

    void rotateRight() {
        switch (direction) {
            case N -> direction = E;
            case S -> direction = W;
            case E -> direction = S;
            case W -> direction = N;
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
    }
}
