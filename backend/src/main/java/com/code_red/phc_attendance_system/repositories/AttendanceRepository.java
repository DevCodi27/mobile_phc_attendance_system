package com.code_red.phc_attendance_system.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.entities.Doctor;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByDate(LocalDate today);

	boolean existsByDoctorAndDate(Doctor doctor, LocalDate date);

	Attendance findByDoctorAndDate(Doctor doctor, LocalDate date);
}
