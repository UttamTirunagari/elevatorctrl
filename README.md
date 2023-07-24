Elevator Control System
=======================

Task:
-----
Designing an API for an elevator control system. 

Scenarios covered are below:

* A person requests an elevator be sent to their current floor
* A person requests that they be brought to a floor
* An elevator car requests all floors that it’s current passengers are servicing (e.g. to light up the buttons that show which floors the car is going to)
* An elevator car requests the next floor it needs to service


Design:
-------
This a springboot application. 
With Elevator, Controller, service, request, response and enums for Direction and status.
Unit tests to test the api too are added.

```
The default port of this project is 8090
API endpoints:
http://localhost:8090/api/v1/elevator/goToFloor
http://localhost:8090/api/v1/elevator/comeToFloor
http://localhost:8090/api/v1/elevator/destinations
http://localhost:8090/api/v1/elevator/nextDestinationFloor

```
Software:

Java Version - 17

Gradle 8.2.1

Steps to build and run:


./gradlew clean build

java -Dserver.port=8090 -jar build/libs/elevatorctrl-0.0.1-SNAPSHOT.jar



