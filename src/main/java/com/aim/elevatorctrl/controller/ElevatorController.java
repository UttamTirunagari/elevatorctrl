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

    /*
    PUT Api, allows the actor to request an elevent be sent to their current floor.
    It takes the floornumber as request, which is part of ElevatorRequest body,
    And returns the Elevator object and message as response
     */
    @ResponseBody
    @PutMapping(value = "/comeToFloor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> comeToFloor(@RequestBody ElevatorRequest elevatorRequest) {
        if(elevatorRequest.getFloorNumber() > numberOfFloors || elevatorRequest.getFloorNumber() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ElevatorResponse(Elevator.getInstance(),"Incorrect floor specified"));
        }
        return elevatorService.setFloorAndDirection(elevatorRequest.getFloorNumber());
    }

    /*
   PUT Api, allows the actor to request an elevent be sent to be bought to a floor.
   It takes the floornumber as request, which is part of ElevatorRequest body,
   And returns the Elevator object and message as response
    */
    @ResponseBody
    @PutMapping(value = "/goToFloor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> goToFloor(@RequestBody ElevatorRequest elevatorRequest) {
        if(elevatorRequest.getFloorNumber() > numberOfFloors || elevatorRequest.getFloorNumber() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ElevatorResponse(Elevator.getInstance(),"Incorrect floor specified"));
        }
          return elevatorService.setFloorAndDirection(elevatorRequest.getFloorNumber());
    }

    /*
    GET Api, provides you a list of destination floors currently in place
     */
    @GetMapping("/destinations")
    public ResponseEntity<String> getDestinations() {
        return ResponseEntity.ok(elevatorService.returnDestinationFloor());
    }

    /*
    GET Api to return the next destination floor
     */
    @GetMapping("/nextDestinationFloor")
    public ResponseEntity<Integer> getNextDestinationFloor() {
        return ResponseEntity.ok(elevatorService.returnNextDestination());
    }
}
