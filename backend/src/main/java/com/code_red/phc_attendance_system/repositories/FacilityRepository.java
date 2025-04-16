package com.code_red.phc_attendance_system.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.dto.FacilityNameDTO;
import com.code_red.phc_attendance_system.entities.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
	
	List<Facility> findByBlock(String block);
	
	@Query("SELECT DISTINCT f.block FROM Facility f")
	List<String> findAllBlocks();
	
    @Query("SELECT f FROM Facility f WHERE f.block = :block")
    List<Facility> findFacilitiesByBlock(@Param("block") String block);

    Optional<Facility> findById(Long id);

    List<Facility> findByDistrict(String district);
    
    @Query("SELECT new com.code_red.phc_attendance_system.dto.FacilityNameDTO(f.id, f.name) FROM Facility f")
    List<FacilityNameDTO> findAllFacilityNames();
}
