package com.code_red.phc_attendance_system.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Region;
import com.code_red.phc_attendance_system.repositories.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	public boolean isDoctorInsideRegion(double latitude, double longitude) {
	    UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Doctor doctor = doctorService.findByEmail(userDetails.getUsername()).orElse(null);
	    
	    if (doctor == null || doctor.getFacility() == null || doctor.getFacility().getRegion() == null) {
	        return false; // No region assigned to user
	    }

	    Region region = doctor.getFacility().getRegion();
	    String pointWKT = String.format("POINT(%f %f)", longitude, latitude);
	    
	    Integer result = regionRepository.isPointInsideRegion(region.getId(), pointWKT);
	    boolean status = result != null && result == 1;
	    return status;
	}
	
    public boolean sendAlertIfOutsideRegion(double latitude, double longitude) {
        boolean status = isDoctorInsideRegion(latitude, longitude);
    	if (!status) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<Doctor> doctorOpt = doctorService.findByEmail(userDetails.getUsername());

            if (doctorOpt.isEmpty()) return status;

            Doctor doctor = doctorOpt.get();
//            userService.findDHO(doctor.getFacility()).ifPresent(dho -> {
//                logger.info("Sending alert email to DHO: {}", dho.getEmail());
//                emailService.sendAlert(doctor.getDoctorId(), doctor.getFullName(), dho.getEmail());
//            });
            
        }
    	return status;
    }
}

