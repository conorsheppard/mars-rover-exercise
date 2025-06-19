package com.conorsheppard.model;

import org.junit.jupiter.api.Test;

import static com.conorsheppard.model.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {
    @Test
    void testRoverTurnLeft() {
        var rover = new Rover(new Point(0, 0), N, new Board(4, 4));
        rover.turnLeft();
        assertEquals(new Rover(new Point(0, 0), W, new Board(4, 4)), rover);
        rover.turnLeft();
        assertEquals(new Rover(new Point(0, 0), S, new Board(4, 4)), rover);
        rover.turnLeft();
        assertEquals(new Rover(new Point(0, 0), E, new Board(4, 4)), rover);
        rover.turnLeft();
        assertEquals(new Rover(new Point(0, 0), N, new Board(4, 4)), rover);
    }

    @Test
    void testRoverTurnRight() {
        var rover = new Rover(new Point(0, 0), N, new Board(4, 4));
        rover.turnRight();
        assertEquals(new Rover(new Point(0, 0), E, new Board(4, 4)), rover);
        rover.turnRight();
        assertEquals(new Rover(new Point(0, 0), S, new Board(4, 4)), rover);
        rover.turnRight();
        assertEquals(new Rover(new Point(0, 0), W, new Board(4, 4)), rover);
        rover.turnRight();
        assertEquals(new Rover(new Point(0, 0), N, new Board(4, 4)), rover);
    }

    @Test
    void testRoverMoveForward() {
        var rover = new Rover(new Point(0, 0), N, new Board(4, 4));
        rover.moveForward();
        assertEquals(new Point(0, 1), rover.getPosition());
        assertEquals(N, rover.getDirection());
        var expectedBoard = new Board(4, 4);
        var occupiedMap = expectedBoard.getOccupied();
        occupiedMap.put(new Point(0, 1), rover);
        assertEquals(expectedBoard, rover.getBoard());
    }
}
