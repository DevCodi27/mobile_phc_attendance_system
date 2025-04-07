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

import com.code_red.phc_attendance_system.dto.FacilityDTO;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Region;
import com.code_red.phc_attendance_system.services.FacilityService;
import com.code_red.phc_attendance_system.services.RegionService;
import com.code_red.phc_attendance_system.util.PolygonConverter;

@RestController
@RequestMapping("/api/facilities")
public class FacilityContoller {
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private RegionService regionService;
	@GetMapping("/blocks")
	private ResponseEntity<List<String>> getAllBlocks(){
		return new ResponseEntity<>(facilityService.getAllBlocks(), HttpStatus.OK);
	}
	
	@GetMapping("/{block}/facilities")
	private ResponseEntity<List<Facility>> getFacilitiesByBlock(@PathVariable String block){
		return new ResponseEntity<>(facilityService.findByBlock(block), HttpStatus.OK);
	}
	
    @PostMapping("/register")
    public ResponseEntity<Facility> registerFacility(@RequestBody FacilityDTO dto) {
        Region region = new Region();
        region.setBoundary(PolygonConverter.convertToPolygon(dto.getRegion()));
        regionService.save(region);

        Facility facility = new Facility();
        facility.setName(dto.getName());
        facility.setBlock(dto.getBlock());
        facility.setDistrict(dto.getDistrict());
        facility.setFaciliy_type(dto.getFacilityType());
        facility.setRegion(region);

        facilityService.save(facility);

        return ResponseEntity.ok(facility);
    }


}
