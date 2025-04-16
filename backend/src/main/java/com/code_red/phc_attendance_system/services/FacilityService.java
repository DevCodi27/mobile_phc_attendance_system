package com.code_red.phc_attendance_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.FacilityNameDTO;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.repositories.FacilityRepository;

@Service
public class FacilityService {
	@Autowired
	private FacilityRepository facilityRepository;
	
	public List<Facility> getAllFacility(){
		return facilityRepository.findAll();
	}
	
	public Facility findById(Long id) {
		return facilityRepository.findById(id).get();
	}
	public List<String> getAllBlocks(){
		return facilityRepository.findAllBlocks();
	}
	
	public List<Facility> findByBlock(String block){
		return facilityRepository.findByBlock(block);
	}
	
	public Facility save(Facility facility) {
		return facilityRepository.save(facility);
	}
	
	public List<FacilityNameDTO> getAllFacilityNames() {
        return facilityRepository.findAllFacilityNames();
    }
	
//	public Facility findByBlock(String block) {
//	    return facilityRepository.findByBlock(block);
//	}


//	public Map<String, List<Facility>> getFacilitiesByBlocks() {
//		List<String> allBlocks = getAllBlocks();
//		Map<String, List<Facility>> facilitiesByBlocks = new HashMap<>();
//		for(String block:allBlocks) {
//			facilitiesByBlocks.put(block, facilityRepository.findFacilitiesByBlock(block))		
//		}
//
//		return facilitiesByBlocks;
//	}
}
