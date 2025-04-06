package com.code_red.phc_attendance_system.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	
	public List<Attendance> findByDate(LocalDate today);
}
