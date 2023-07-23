package com.aim.elevatorctrl.enums;

public enum ElevatorDirection {
    ELEVATOR_UP("Going Up"),
    ELEVATOR_DOWN("Going Down"),
    ELEVATOR_IDLE("Idle");

    private String value;

    ElevatorDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}