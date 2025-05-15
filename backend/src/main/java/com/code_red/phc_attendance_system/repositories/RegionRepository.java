package com.code_red.phc_attendance_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	@Query(value = "SELECT COUNT(*) FROM regions r WHERE ST_Within(ST_GeomFromText(:pointWKT, 4326), boundary) AND r.id = :regionId", nativeQuery = true)
	Integer isPointInsideRegion(@Param("regionId") Long regionId, @Param("pointWKT") String pointWKT);

}
