package com.code_red.phc_attendance_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/register")
	public ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorDTO doctordto) {	
		return new ResponseEntity<>(doctorService.register(doctordto), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public List<Doctor> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
	
//	@GetMapping("/{block}")
//	public ResponseEntity<List<Doctor>> getAllDoctorsByBlock(@PathVariable String block){
//		return new ResponseEntity<>(doctorService.getAllDoctorsByBlock(block), HttpStatus.ACCEPTED);
//	}
}
