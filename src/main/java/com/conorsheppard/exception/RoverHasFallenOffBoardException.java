package com.conorsheppard.exception;

public class RoverHasFallenOffBoardException extends RuntimeException {
    public RoverHasFallenOffBoardException(int x, int y) {
        super("Rover has fallen off the board at position: (" + x + ", " + y + ")");
    }
}
