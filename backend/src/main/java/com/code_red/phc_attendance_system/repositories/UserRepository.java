package com.code_red.phc_attendance_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByEmail(String email);
	    
	@Query("SELECT u FROM AppUser u JOIN FETCH u.roles r WHERE u.district = :districtName AND r.name = 'DHO'")
	Optional<AppUser> findByDistrictAndRole(@Param("districtName") String districtName);


}
