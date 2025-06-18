package com.conorsheppard.exercise;

import com.conorsheppard.exception.ObstacleInTheWayException;
import com.conorsheppard.exception.RoverHasFallenOffBoardException;
import lombok.Data;

import static com.conorsheppard.exercise.Direction.*;

@Data
public class MarsRover {
    private int[] position;
    private Direction direction;
    private int[][] board;


    public MarsRover(int[] position, Direction direction, int[][] board) {
        this.board = board;
        if (!isInBounds(position[0], position[1])) throw new RoverHasFallenOffBoardException(position[0], position[1]);
        if(isObstacle(position[0], position[1]))  throw new ObstacleInTheWayException(position[0], position[1]);
        this.position = position;
        this.direction = direction;
        board[position[1]][position[0]] = 1; // set initial position
    }

    public void executeCommand(String command) {
        // Commands: "L" (rotate left), "R" (rotate right), "M" (move forward in the current direction)
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

        if (!isInBounds(nextX, nextY)) {
            board[position[1]][position[0]] = 0; // rover has fallen off, so free up its previous position
            throw new RoverHasFallenOffBoardException(nextX, nextY);
        }
        if (isObstacle(nextX, nextY)) throw new ObstacleInTheWayException(nextX, nextY);
        // x and y are reverse as arrays represent a top-left origin coordinate system, board[y][x] == board[row][col]
        board[nextY][nextX] = 1; // move the rover on the board
        board[position[1]][position[0]] = 0; // free up its previous position
        position[0] = nextX;
        position[1] = nextY;
    }

    private boolean isObstacle(int x, int y) {
        return board[y][x] == 1;
    }

    private void rotateLeft() {
        switch (direction) {
            case N -> direction = W;
            case S -> direction = E;
            case E -> direction = N;
            case W -> direction = S;
        }
    }

    private void rotateRight() {
        switch (direction) {
            case N -> direction = E;
            case S -> direction = W;
            case E -> direction = S;
            case W -> direction = N;
        }
    }

    private boolean isInBounds(int x, int y) {
        return y >= 0 && y < board.length && x >= 0 && x < board[y].length;
    }
}
