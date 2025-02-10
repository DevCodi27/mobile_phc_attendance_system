package com.code_red.phc_attendance_system.repositories;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
	
	@Query("SELECT COUNT(r) > 0 FROM Region r WHERE r.id = :regionId AND ST_Contains(r.boundary, :point) = true") 
	boolean isPointInsideRegion(Long regionId, Point point);
}
