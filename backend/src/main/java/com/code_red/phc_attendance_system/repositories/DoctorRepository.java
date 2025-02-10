package com.code_red.phc_attendance_system.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	public Optional<Doctor> findByEmail(String email);

//	public List<Doctor> findByBlock(String block);

}
