package com.aim.elevatorctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorctrlApplication {

	public static void main(String[] args) {
	 	try {
			SpringApplication.run(ElevatorctrlApplication.class, args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
	 		e.printStackTrace();
		}
	}
}
