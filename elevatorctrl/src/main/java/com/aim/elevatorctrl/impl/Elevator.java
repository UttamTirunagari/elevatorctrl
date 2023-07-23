package com.aim.elevatorctrl.impl;

import com.aim.elevatorctrl.enums.ElevatorDirection;
import com.aim.elevatorctrl.enums.ElevatorStatus;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Elevator {

    private static Elevator instance;

    private int currentFloor;

    private Set<Integer> destinationFloor;

    private ElevatorDirection direction;

    private ElevatorStatus status;

    private int numberOfFloors;

    // Private constructor to prevent direct instantiation
    private Elevator() {
        currentFloor = 1;
        direction = ElevatorDirection.ELEVATOR_IDLE;
        status = ElevatorStatus.ELEVATOR_FUNCTIONAL;
        destinationFloor = new HashSet<>();
    }

    public static Elevator getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new Elevator();
            }
        }
        return instance;
    }

    public Set<Integer> getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        if(destinationFloor >0 && destinationFloor < numberOfFloors)
        this.destinationFloor.add(destinationFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getDirectionValue() {
        return direction.getValue();
    }

    public ElevatorDirection getDirection() {
        return direction;
    }

    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }

}
