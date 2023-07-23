package com.aim.elevatorctrl.controller;

import com.aim.elevatorctrl.impl.Elevator;
import com.aim.elevatorctrl.request.ElevatorRequest;
import com.aim.elevatorctrl.response.ElevatorResponse;
import com.aim.elevatorctrl.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Elevator Controller
 *
 * @author uttamtirunagari
 */

@RestController
@RequestMapping("/api/v1/elevator")
public class ElevatorController {

    @Autowired
    private ElevatorService elevatorService;

    @Value("${numberOfFloors:10}")
    private int numberOfFloors;

    @ResponseBody
    @PutMapping(value = "/comeToFloor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> comeToFloor(@RequestBody ElevatorRequest elevatorRequest) {
        if(elevatorRequest.getFloorNumber() > numberOfFloors || elevatorRequest.getFloorNumber() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ElevatorResponse(Elevator.getInstance(),"Incorrect floor specified"));
        }
        return elevatorService.setFloorAndDirection(elevatorRequest.getFloorNumber());
    }

    @ResponseBody
    @PutMapping(value = "/goToFloor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> goToFloor(@RequestBody ElevatorRequest elevatorRequest) {
        if(elevatorRequest.getFloorNumber() > numberOfFloors || elevatorRequest.getFloorNumber() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ElevatorResponse(Elevator.getInstance(),"Incorrect floor specified"));
        }
          return elevatorService.setFloorAndDirection(elevatorRequest.getFloorNumber());
    }

    @GetMapping("/destinations")
    public ResponseEntity<String> getDestinations() {
        return ResponseEntity.ok(elevatorService.returnDestinationFloor());
    }

    @GetMapping("/nextDestinationFloor")
    public ResponseEntity<Integer> getNextDestinationFloor() {
        return ResponseEntity.ok(elevatorService.returnNextDestination());
    }
}
