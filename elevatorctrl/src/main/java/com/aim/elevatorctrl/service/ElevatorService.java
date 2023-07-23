package com.aim.elevatorctrl.service;

import com.aim.elevatorctrl.enums.ElevatorDirection;
import com.aim.elevatorctrl.impl.Elevator;
import com.aim.elevatorctrl.response.ElevatorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;

/**
 * Elevator Service class
 *
 * @author uttamtirunagari
 */
@Component
public class ElevatorService {

    Elevator elevator = Elevator.getInstance();

    @Value("${numberOfFloors:10}")
    private int numberOfFloors;

    @PostConstruct
    private void init() {
        elevator.setNumberOfFloors(numberOfFloors);
    }

    public ResponseEntity<ElevatorResponse> setFloorAndDirection(int floorNumber) {
        elevator.setDestinationFloor(floorNumber);
        if(elevator.getCurrentFloor() < floorNumber) {
            elevator.setDirection(ElevatorDirection.ELEVATOR_UP);
        }
        else {
            elevator.setDirection(ElevatorDirection.ELEVATOR_DOWN);
        }
        return ResponseEntity.ok(new ElevatorResponse(elevator,"Success"));
    }

    public int returnNextDestination() {
        if(!elevator.getDestinationFloor().isEmpty()) {
            if (elevator.getDirection() == ElevatorDirection.ELEVATOR_DOWN) {
                //search for all floors below the currentfloor in the destinationFloor set
                for (int i = elevator.getCurrentFloor() - 1; i >= 1; i--) {
                    if (elevator.getDestinationFloor().contains(i)) {
                        return i;
                    }
                }
            } else if (elevator.getDirection() == ElevatorDirection.ELEVATOR_UP) {
                //search for all floors above the currentfloor in the destinationfloor set
                for (int i = elevator.getCurrentFloor() + 1; i <= elevator.getNumberOfFloors(); i++) {
                    if (elevator.getDestinationFloor().contains(i)) {
                        return i;
                    }
                }
            }
        }
        return elevator.getCurrentFloor();
    }

    public String returnDestinationFloor() {
        return elevator.getDestinationFloor().toString();
    }
}
