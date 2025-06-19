package com.conorsheppard.controller;

import com.conorsheppard.dto.RoverRequest;
import com.conorsheppard.model.Direction;
import com.conorsheppard.model.Point;
import com.conorsheppard.model.Rover;
import com.conorsheppard.service.RoverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoverController.class)
class RoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoverService roverService; // = Mockito.mock(RoverService.class);

    @Autowired
    private ObjectMapper objectMapper;

    private UUID roverId;

    @BeforeEach
    void setUp() {
        roverId = UUID.randomUUID();
    }

    @Test
    void testCreateRover() throws Exception {
        Mockito.when(roverService.createRover(2, 3, Direction.E)).thenReturn(roverId);

        RoverRequest request = new RoverRequest(2, 3, Direction.E);

        mockMvc.perform(post("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string(roverId.toString()));
    }

    @Test
    void testMoveRover() throws Exception {
        Mockito.when(roverService.moveForward(roverId)).thenReturn(true);

        mockMvc.perform(post("/api/v1/" + roverId + "/move"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.moved", is(true)));
    }

    @Test
    void testTurnLeft() throws Exception {
        Mockito.when(roverService.turnLeft(roverId)).thenReturn(true);

        mockMvc.perform(post("/api/v1/" + roverId + "/left"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.turnedLeft", is(true)));
    }

    @Test
    void testTurnRight() throws Exception {
        Mockito.when(roverService.turnRight(roverId)).thenReturn(true);

        mockMvc.perform(post("/api/v1/" + roverId + "/right"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.turnedRight", is(true)));
    }

    @Test
    void testGetRoverState() throws Exception {
        var position = new Point(4, 5);
        Direction direction = Direction.N;

        Rover mockRover = Mockito.mock(Rover.class);
        Mockito.when(mockRover.getPosition()).thenReturn(position);
        Mockito.when(mockRover.getDirection()).thenReturn(direction);
        Mockito.when(roverService.getRover(roverId)).thenReturn(mockRover);

        mockMvc.perform(get("/api/v1/" + roverId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x", is(4)))
                .andExpect(jsonPath("$.position.y", is(5)))
                .andExpect(jsonPath("$.direction", is("N")));
    }
}
