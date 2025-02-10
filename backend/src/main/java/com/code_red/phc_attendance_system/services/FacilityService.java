package com.code_red.phc_attendance_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.repositories.FacilityRepository;

@Service
public class FacilityService {
	@Autowired
	private FacilityRepository facilityRepository;
	
	public List<Facility> getAllFacility(){
		return facilityRepository.findAll();
	}
	
	public List<Facility> findByBlock(String block){
		return facilityRepository.findByBlock(block);
	}
}
