package com.aim.elevatorctrl.response;

import com.aim.elevatorctrl.impl.Elevator;
import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * Elevator Service class
 *
 * @author uttamtirunagari
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ElevatorResponse {
    private Elevator elevator;

    private String message;

    public ElevatorResponse(Elevator elevator, String message) {
        this.elevator = elevator;
        this.message = message;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
