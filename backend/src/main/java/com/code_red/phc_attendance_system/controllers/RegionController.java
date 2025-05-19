package com.code_red.phc_attendance_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.dto.GPSResponseDTO;
import com.code_red.phc_attendance_system.dto.LocationDTO;
import com.code_red.phc_attendance_system.services.RegionService;

@RestController
@RequestMapping("/gps")
public class RegionController {
	@Autowired
	private RegionService regionService;

	@PostMapping("/validate")
	public ResponseEntity<GPSResponseDTO> verifyGPSWithinRegion(@RequestBody LocationDTO locationDTO) {
		return new ResponseEntity<>(
				new GPSResponseDTO(
						regionService.sendAlertIfOutsideRegion(locationDTO.getLatitude(), locationDTO.getLongitude())),
				HttpStatus.OK);
	}
}
