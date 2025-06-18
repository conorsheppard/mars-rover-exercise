package com.conorsheppard.dto;

import com.conorsheppard.model.Direction;

import java.awt.*;

public record RoverState(Point position, Direction direction) {}
