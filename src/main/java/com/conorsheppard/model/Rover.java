package com.conorsheppard.model;

import lombok.Data;

@Data
public class Rover {
    private Point position;
    private Direction direction;
    private Board board;

    public Rover(Point position, Direction direction, Board board) {
        this.position = position;
        this.direction = direction;
        this.board = board;
    }

    public boolean moveForward() {
        Point current = new Point(position.x(), position.y());
        Point next = new Point(current.x() + direction.x, current.y() + direction.y);

        if (board.moveRover(this, current, next)) {
            this.setPosition(new Point(next.x(), next.y()));
            return true;
        }
        return false;
    }

    public boolean turnLeft() {
        direction = direction.turnLeft();
        return true;
    }

    public boolean turnRight() {
        direction = direction.turnRight();
        return true;
    }
}
