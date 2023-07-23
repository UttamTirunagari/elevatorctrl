package com.aim.elevatorctrl;

import com.aim.elevatorctrl.controller.ElevatorController;
import com.aim.elevatorctrl.impl.Elevator;
import com.aim.elevatorctrl.request.ElevatorRequest;
import com.aim.elevatorctrl.service.ElevatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ElevatorController.class)
public class ElevatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Elevator elevator;

    @MockBean
    private ElevatorService elevatorService;

    @InjectMocks
    ElevatorController elevatorController;

    private ElevatorRequest elevatorRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        elevator = Elevator.getInstance();
        elevatorRequest = new ElevatorRequest();
    }

    @Test
    public void testComeToFloor_Success() throws Exception {
        int floorNumber = 5;
        elevatorRequest.setFloorNumber(floorNumber);
        mockMvc.perform(put("/api/v1/elevator/comeToFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(elevatorRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testComeToFloor_InvalidFloor() throws Exception {
        int invalidFloorNumber = 15;
        elevatorRequest.setFloorNumber(invalidFloorNumber);
        mockMvc.perform(put("/api/v1/elevator/comeToFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(elevatorRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGoToFloor_Success() throws Exception {
        int floorNumber = 5;
        elevatorRequest.setFloorNumber(floorNumber);
        mockMvc.perform(put("/api/v1/elevator/goToFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(elevatorRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGoToFloor_InvalidFloor() throws Exception {
        int invalidFloorNumber = 15;
        elevatorRequest.setFloorNumber(invalidFloorNumber);
        mockMvc.perform(put("/api/v1/elevator/goToFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(elevatorRequest)))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testNextDestinationFloor() throws Exception {
        when(elevatorService.returnNextDestination()).thenReturn(4);

        mockMvc.perform(get("/api/v1/elevator/nextDestinationFloor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("4"));
    }

    @Test
    public void testDestinationFloors() throws Exception {
        when(elevatorService.returnDestinationFloor()).thenReturn("[4, 9]");

        mockMvc.perform(get("/api/v1/elevator/destinations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[4, 9]"));
    }
}

