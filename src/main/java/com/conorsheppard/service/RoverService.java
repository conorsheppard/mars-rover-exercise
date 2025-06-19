package com.conorsheppard.service;

import com.conorsheppard.exception.ObstacleInTheWayException;
import com.conorsheppard.model.Board;
import com.conorsheppard.model.Direction;
import com.conorsheppard.model.Point;
import com.conorsheppard.model.Rover;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoverService {
    private final Board board = new Board(10, 10);
    private final Map<UUID, Rover> rovers = new ConcurrentHashMap<>();

    public UUID createRover(int x, int y, Direction direction) {
        UUID id = UUID.randomUUID();
        Rover rover = new Rover(new Point(x, y), direction, board);
        if (board.registerRover(rover, new Point(x, y))) {
            rovers.put(id, rover);
            return id;
        }
        throw new ObstacleInTheWayException(x, y);
    }

    public Rover getRover(UUID id) {
        return Optional.ofNullable(rovers.get(id))
                .orElseThrow(() -> new NoSuchElementException("Rover not found"));
    }

    public boolean moveForward(UUID id) {
        Rover rover = getRover(id);
        return rover.moveForward();
    }

    public boolean turnLeft(UUID id) {
        Rover rover = getRover(id);
        return rover.turnLeft();
    }

    public boolean turnRight(UUID id) {
        Rover rover = getRover(id);
        return rover.turnRight();
    }
}

