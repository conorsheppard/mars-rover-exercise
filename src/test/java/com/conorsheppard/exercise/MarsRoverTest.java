package com.conorsheppard.exercise;

import com.conorsheppard.exception.ObstacleInTheWayException;
import com.conorsheppard.exception.RoverHasFallenOffBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.conorsheppard.exercise.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTest {
    @ParameterizedTest(name = "Mars Rover #{index}")
    @MethodSource("testCases")
    @DisplayName("Mars Rover Test Cases")
    void test(String command, MarsRover inputRover, MarsRover expectedRover) {
        inputRover.executeCommand(command);
        assertEquals(expectedRover, inputRover);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Test rotate left from North
                Arguments.of("L",
                        new MarsRover(new int[]{0, 0}, N, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, W, new int[4][4])
                ),
                // Test rotate left from South
                Arguments.of("L",
                        new MarsRover(new int[]{0, 0}, S, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, E, new int[4][4])
                ),
                // Test rotate left from East
                Arguments.of("L",
                        new MarsRover(new int[]{0, 0}, E, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, N, new int[4][4])
                ),
                // Test rotate left from West
                Arguments.of("L",
                        new MarsRover(new int[]{0, 0}, W, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, S, new int[4][4])
                ),


                // Test rotate right from North
                Arguments.of("R",
                        new MarsRover(new int[]{0, 0}, N, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, E, new int[4][4])
                ),
                // Test rotate right from South
                Arguments.of("R",
                        new MarsRover(new int[]{0, 0}, S, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, W, new int[4][4])
                ),
                // Test rotate right from East
                Arguments.of("R",
                        new MarsRover(new int[]{0, 0}, E, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, S, new int[4][4])
                ),
                // Test rotate right from West
                Arguments.of("R",
                        new MarsRover(new int[]{0, 0}, W, new int[4][4]),
                        new MarsRover(new int[]{0, 0}, N, new int[4][4])
                ),

                Arguments.of("M",
                        new MarsRover(new int[]{0, 0}, N, new int[4][4]),
                        new MarsRover(new int[]{0, 1}, N, new int[4][4])
                )
        );
    }

    @Test
    void testRoverStartPosition() {
        var rover = new MarsRover(new int[]{0, 0}, N, new int[4][4]);
        assertEquals(1, rover.getBoard()[0][0]);
    }

    @Test
    void testRoverWithMultipleCommands() {
        String commands = "MMM"; // rover will move to the edge of the board
        var rover = new MarsRover(new int[]{0, 0}, N, new int[4][4]);
        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
        assertEquals(rover, new MarsRover(new int[]{0, 3}, N, new int[4][4]));
    }

    @Test
    void testMoveOnceInEachDirection() {
        String commands = "MRMRMRMR"; // rover will move to the edge of the board
        var rover = new MarsRover(new int[]{0, 0}, N, new int[4][4]);
        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
        assertEquals(rover, new MarsRover(new int[]{0, 0}, N, new int[4][4]));
    }

    @Test
    void testRoverFallsOffBoardAndExceptionIsThrown() {
        String commands = "MMM"; // rover will fall of board
        var rover = new MarsRover(new int[]{0, 0}, N, new int[4][4]);
        Arrays.stream(commands.split("")).forEach(rover::executeCommand);
        String commandToFallOffGrid = "M";
        RoverHasFallenOffBoardException exception =
                assertThrows(RoverHasFallenOffBoardException.class, () -> rover.executeCommand(commandToFallOffGrid));
        assertArrayEquals(new int[4][4], rover.getBoard()); // rover has fallen off and board is empty again
        assertEquals("Rover has fallen off the board at position: (0, 4)", exception.getMessage());
    }

    @Test
    void obstacleCheckOnRoverInstantiation() {
        var board = new int[4][4];
        new MarsRover(new int[]{0, 0}, N, board);
        assertEquals("Space is already occupied by another rover at position: (0, 0)",
                assertThrows(ObstacleInTheWayException.class, () -> new MarsRover(new int[]{0, 0}, N, board))
                        .getMessage());
    }

    @Test
    void boundsCheckOnRoverInstantiation() {
        assertEquals("Rover has fallen off the board at position: (0, -1)",
                assertThrows(RoverHasFallenOffBoardException.class, () -> new MarsRover(new int[]{0, -1}, N, new int[][]{}))
                        .getMessage());
    }

    @Test
    void testRoverCantMoveIntoOccupiedSpace() {
        var board = new int[4][4]; // both hold a reference to the same board
        var rover1 = new MarsRover(new int[]{0, 0}, N, board);
        var _ = new MarsRover(new int[]{0, 1}, N, board);

        assertEquals("Space is already occupied by another rover at position: (0, 1)",
                assertThrows(ObstacleInTheWayException.class, () -> rover1.executeCommand("M"))
                        .getMessage());
    }
}
