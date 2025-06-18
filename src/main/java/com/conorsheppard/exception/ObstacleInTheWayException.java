package com.conorsheppard.exception;

public class ObstacleInTheWayException extends RuntimeException {
    public ObstacleInTheWayException(int x, int y) {
        super("Space is already occupied by another rover at position: (" + x + ", " + y + ")");
    }
}
