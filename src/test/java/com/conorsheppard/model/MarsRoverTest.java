package com.conorsheppard.model;

import com.conorsheppard.exception.ObstacleInTheWayException;
import com.conorsheppard.exception.RoverHasFallenOffBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.conorsheppard.model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

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

//    @ParameterizedTest(name = "Mars Rover #{index}")
//    @MethodSource("testCases")
//    @DisplayName("Mars Rover Test Cases")
//    void test(String command, Rover inputRover, Rover expectedRover) {
//        inputRover.moveForward();
//        assertEquals(expectedRover, inputRover);
//    }
//
//    static Stream<Arguments> testCases() {
//        return Stream.of(
//                // Test rotate left from North
//                Arguments.of("L",
//                        new Rover(new int[]{0, 0}, N, new int[4][4]),
//                        new Rover(new int[]{0, 0}, W, new int[4][4])
//                ),
//                // Test rotate left from South
//                Arguments.of("L",
//                        new Rover(new int[]{0, 0}, S, new int[4][4]),
//                        new Rover(new int[]{0, 0}, E, new int[4][4])
//                ),
//                // Test rotate left from East
//                Arguments.of("L",
//                        new Rover(new int[]{0, 0}, E, new int[4][4]),
//                        new Rover(new int[]{0, 0}, N, new int[4][4])
//                ),
//                // Test rotate left from West
//                Arguments.of("L",
//                        new Rover(new int[]{0, 0}, W, new int[4][4]),
//                        new Rover(new int[]{0, 0}, S, new int[4][4])
//                ),
//
//
//                // Test rotate right from North
//                Arguments.of("R",
//                        new Rover(new int[]{0, 0}, N, new int[4][4]),
//                        new Rover(new int[]{0, 0}, E, new int[4][4])
//                ),
//                // Test rotate right from South
//                Arguments.of("R",
//                        new Rover(new int[]{0, 0}, S, new int[4][4]),
//                        new Rover(new int[]{0, 0}, W, new int[4][4])
//                ),
//                // Test rotate right from East
//                Arguments.of("R",
//                        new Rover(new int[]{0, 0}, E, new int[4][4]),
//                        new Rover(new int[]{0, 0}, S, new int[4][4])
//                ),
//                // Test rotate right from West
//                Arguments.of("R",
//                        new Rover(new int[]{0, 0}, W, new int[4][4]),
//                        new Rover(new int[]{0, 0}, N, new int[4][4])
//                ),
//
//                Arguments.of("M",
//                        new Rover(new int[]{0, 0}, N, new int[4][4]),
//                        new Rover(new int[]{0, 1}, N, new int[4][4])
//                )
//        );
//    }
//
//    @Test
//    void testRoverStartPosition() {
//        var rover = new Rover(new int[]{0, 0}, N, new int[4][4]);
//        assertEquals(1, rover.getBoard()[0][0]);
//    }
//
//    @Test
//    void testRoverWithMultipleCommands() {
//        String commands = "MMM"; // rover will move to the edge of the board
//        var rover = new Rover(new int[]{0, 0}, N, new int[4][4]);
//        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
//        assertEquals(rover, new Rover(new int[]{0, 3}, N, new int[4][4]));
//    }
//
//    @Test
//    void testMoveOnceInEachDirection() {
//        String commands = "MRMRMRMR"; // rover will move to the edge of the board
//        var rover = new Rover(new int[]{0, 0}, N, new int[4][4]);
//        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
//        assertEquals(rover, new Rover(new int[]{0, 0}, N, new int[4][4]));
//    }
//
//    @Test
//    void testRoverFallsOffBoardAndExceptionIsThrown() {
//        String commands = "MMM"; // rover will fall of board
//        var rover = new Rover(new int[]{0, 0}, N, new int[4][4]);
//        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
//        String commandToFallOffGrid = "M";
//        RoverHasFallenOffBoardException exception =
//                assertThrows(RoverHasFallenOffBoardException.class, () -> rover.executeCommand(commandToFallOffGrid));
//        assertArrayEquals(new int[4][4], rover.getBoard()); // rover has fallen off and board is empty again
//        assertEquals("Rover has fallen off the board at position: (0, 4)", exception.getMessage());
//    }
//
//    @Test
//    void obstacleCheckOnRoverInstantiation() {
//        var board = new int[4][4];
//        new Rover(new int[]{0, 0}, N, board);
//        assertEquals("Space is already occupied by another rover at position: (0, 0)",
//                assertThrows(ObstacleInTheWayException.class, () -> new Rover(new int[]{0, 0}, N, board))
//                        .getMessage());
//    }
//
//    @Test
//    void boundsCheckOnRoverInstantiation() {
//        assertEquals("Rover has fallen off the board at position: (0, -1)",
//                assertThrows(RoverHasFallenOffBoardException.class, () -> new Rover(new int[]{0, -1}, N, new int[][]{}))
//                        .getMessage());
//    }
//
//    @Test
//    void testRoverCantMoveIntoOccupiedSpace() {
//        var board = new int[4][4]; // both hold a reference to the same board
//        var rover1 = new Rover(new int[]{0, 0}, N, board);
//        var _ = new Rover(new int[]{0, 1}, N, board);
//
//        assertEquals("Space is already occupied by another rover at position: (0, 1)",
//                assertThrows(ObstacleInTheWayException.class, () -> rover1.executeCommand("M"))
//                        .getMessage());
//    }
}
