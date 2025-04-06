package com.code_red.phc_attendance_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.services.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	
	@PostMapping("/:doctorId")
	public ResponseEntity<Attendance> markAttendance(@PathVariable Long doctorId) {
		return new ResponseEntity<>(attendanceService.markAttendance(doctorId), HttpStatus.CREATED);
	}
	
}
