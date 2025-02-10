package com.code_red.phc_attendance_system.services;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Region;
import com.code_red.phc_attendance_system.repositories.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private UserService userService;
	
	private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
	public boolean findRegionByCoordinates(double latitude, double longitude) {
		
		
		UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AppUser user = userService.findByEmail(userDetails.getUsername()).get();
		if (user == null || user.getFacility() == null || user.getFacility().getRegion() == null) {
            return false; // No region assigned to user
        }
		Region region = user.getFacility().getRegion();
		Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));
		return regionRepository.isPointInsideRegion(region.getId() ,point);
	}

	

}

