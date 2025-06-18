package com.conorsheppard.controller;

import com.conorsheppard.dto.RoverRequest;
import com.conorsheppard.dto.RoverState;
import com.conorsheppard.model.Rover;
import com.conorsheppard.service.RoverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RoverController {
    private final RoverService roverService;

    @PostMapping("/{id}/move")
    public ResponseEntity<?> moveRover(@PathVariable UUID id) {
        var moved = roverService.moveForward(id);
        return ResponseEntity.ok(Map.of("moved", moved));
    }

    @PostMapping("/{id}/left")
    public ResponseEntity<?> turnLeft(@PathVariable UUID id) {
        var turnedLeft = roverService.turnLeft(id);
        return ResponseEntity.ok(Map.of("turnedLeft", turnedLeft));
    }

    @PostMapping("/{id}/right")
    public ResponseEntity<?> turnRight(@PathVariable UUID id) {
        var turnedRight = roverService.turnRight(id);
        return ResponseEntity.ok(Map.of("turnedRight", turnedRight));
    }

    @PostMapping
    public ResponseEntity<String> createRover(@RequestBody RoverRequest req) {
        UUID id = roverService.createRover(req.x(), req.y(), req.direction());
        return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
    }

    @GetMapping("/{id}")
    public RoverState getRover(@PathVariable UUID id) {
        Rover rover = roverService.getRover(id);
        return new RoverState(rover.getPosition(), rover.getDirection());
    }
}

