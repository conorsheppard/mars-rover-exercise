package com.conorsheppard.service;

import com.conorsheppard.exception.ObstacleInTheWayException;
import com.conorsheppard.model.Point;
import com.conorsheppard.model.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.conorsheppard.model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class RoverServiceTest {

    private RoverService roverService;

    @BeforeEach
    void setUp() {
        roverService = new RoverService();
    }

    @Test
    void testCreateRover() {
        UUID id = roverService.createRover(2, 2, N);
        assertNotNull(id);

        Rover rover = roverService.getRover(id);
        assertEquals(new Point(2, 2), rover.getPosition());
        assertEquals(N, rover.getDirection());
    }

    @Test
    void testMoveForwardNorth() {
        UUID id = roverService.createRover(1, 1, N);
        boolean moved = roverService.moveForward(id);
        assertTrue(moved);

        Rover rover = roverService.getRover(id);
        assertEquals(new Point(1, 2), rover.getPosition());
    }

    @Test
    void testTurnLeftAndRight() {
        UUID id = roverService.createRover(0, 0, N);

        roverService.turnLeft(id);  // N -> W
        assertEquals(W, roverService.getRover(id).getDirection());

        roverService.turnRight(id); // W -> N
        assertEquals(N, roverService.getRover(id).getDirection());

        roverService.turnRight(id); // N -> E
        assertEquals(E, roverService.getRover(id).getDirection());
    }

    @Test
    void testBlockDuplicatePlacement() {
        roverService.createRover(3, 3, N);
        assertThrows(ObstacleInTheWayException.class, () ->
                roverService.createRover(3, 3, E)
        );
    }

    @Test
    void testMoveBlockedByAnotherRover() {
        UUID id1 = roverService.createRover(4, 4, N); // Rover 1 at (4, 4)
        UUID id2 = roverService.createRover(4, 5, S); // Rover 2 at (4, 5)

        boolean move1 = roverService.moveForward(id1); // Attempt to move to (4, 5)
        boolean move2 = roverService.moveForward(id2); // Attempt to move to (4, 4)

        assertFalse(move1);
        assertFalse(move2);
    }

    @Test
    void testMoveOutOfBoundsBlocked() {
        UUID id = roverService.createRover(0, 9, N);
        boolean moved = roverService.moveForward(id); // Attempt to go to (0, 10)
        assertFalse(moved);
        assertEquals(new Point(0, 9), roverService.getRover(id).getPosition());
    }
}

