package com.conorsheppard.dto;

import com.conorsheppard.model.Direction;
import com.conorsheppard.model.Point;

public record RoverState(Point position, Direction direction) {}
