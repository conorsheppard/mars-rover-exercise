package com.conorsheppard.dto;

import com.conorsheppard.model.Direction;

public record RoverRequest(int x, int y, Direction direction) {}
