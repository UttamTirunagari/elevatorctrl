package com.aim.elevatorctrl.enums;

public enum ElevatorStatus {
        ELEVATOR_FUNCTIONAL("functional"),
        ELEVATOR_MAINTENANCE("In Maintenance");

        private String value;

        ElevatorStatus(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}
