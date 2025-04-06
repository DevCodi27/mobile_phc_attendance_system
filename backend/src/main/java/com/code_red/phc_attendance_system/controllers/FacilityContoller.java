
package com.code_red.phc_attendance_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.services.FacilityService;

@RestController
@RequestMapping("/api/facilities")
public class FacilityContoller {
	@Autowired
	private FacilityService facilityService;
	@GetMapping("/blocks")
	private ResponseEntity<List<String>> getAllBlocks(){
		return new ResponseEntity<>(facilityService.getAllBlocks(), HttpStatus.OK);
	}
	
	@GetMapping("/{block}/facilities")
	private ResponseEntity<List<Facility>> getFacilitiesByBlock(@PathVariable String block){
		return new ResponseEntity<>(facilityService.findByBlock(block), HttpStatus.OK);
	}
	

}
