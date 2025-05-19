package com.code_red.phc_attendance_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.services.ShiftService;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	@GetMapping("/pending")
	public ResponseEntity<List<Shift>> getPendingShifts() {
		return new ResponseEntity<>(shiftService.getPendingShifts(), HttpStatus.ACCEPTED);
	}
	
	
}
