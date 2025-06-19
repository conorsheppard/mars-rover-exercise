package com.conorsheppard.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Board {
    private final int width;
    private final int height;
    private final Map<Point, Rover> occupied = new HashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public synchronized boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public synchronized boolean isObstacle(Point p) {
        return occupied.containsKey(p);
    }

    public synchronized boolean registerRover(Rover rover, Point p) {
        if (!isInBounds(p.x(), p.y()) || isObstacle(p)) return false;
        occupied.put(p, rover);
        return true;
    }

    public synchronized boolean moveRover(Rover rover, Point from, Point to) {
        if (!isInBounds(to.x(), to.y()) || isObstacle(to)) return false;
        occupied.remove(from);
        occupied.put(to, rover);
        return true;
    }
}
