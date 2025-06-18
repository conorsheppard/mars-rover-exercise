package com.conorsheppard.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.conorsheppard.exercise.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// single user game
// users starts the game by defini size of board, rectangle
// user can deploy rovers, specify co-ords
// user can send commands, one at a time
// move left, right, or forward
// check rovers don't fall off the board or collide
// REST API - api call, state + business logic

public class MarsRoverTest {
    @ParameterizedTest(name = "Mars Rover #{index}")
    @MethodSource("testCases")
    @DisplayName("Mars Rover Test Cases")
    void test(String command, MarsRover input, MarsRover expected) {
        input.executeCommand(command);
        assertEquals(expected, input);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Commands: L, R, M
                Arguments.of("L",
                        new MarsRover(new int[]{0, 0}, N, new Board(4, 4)),
                        new MarsRover(new int[]{0, 0}, W, new Board(4, 4))
                ),

                Arguments.of("M",
                        new MarsRover(new int[]{0, 0}, N, new Board(4, 4)),
                        new MarsRover(new int[]{0, 1}, N, new Board(4, 4))
                )
        );
    }
}
