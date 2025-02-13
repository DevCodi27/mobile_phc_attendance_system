package com.code_red.phc_attendance_system.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Region;
import com.code_red.phc_attendance_system.repositories.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private DoctorService doctorService;
	
	public boolean findRegionByCoordinates(double latitude, double longitude) {
	    UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Doctor doctor = doctorService.findByEmail(userDetails.getUsername()).orElse(null);
	    
	    if (doctor == null || doctor.getFacility() == null || doctor.getFacility().getRegion() == null) {
	        return false; // No region assigned to user
	    }

	    Region region = doctor.getFacility().getRegion();
	    String pointWKT = String.format("POINT(%f %f)", longitude, latitude);
	    
	    Integer result = regionRepository.isPointInsideRegion(region.getId(), pointWKT);
	    
	    return result != null && result == 1;
	}
}

