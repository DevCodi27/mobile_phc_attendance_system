package com.code_red.phc_attendance_system.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.enums.AttendanceStatus;
import com.code_red.phc_attendance_system.repositories.AttendanceRepository;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	private	List<Attendance> getAttendanceByToday(){
		LocalDate today = LocalDate.now();
		return attendanceRepository.findByDate(today);
	}
	
	public Attendance markAttendance(Long id) {
		Attendance attendance = new Attendance();
		attendance.setStatus(AttendanceStatus.PRESENT);
		attendance.setDoctor(doctorRepository.findById(id).get());
		attendance.setCheckInTime(LocalTime.now());
		attendance.setDate(LocalDate.now());
		return attendanceRepository.save(attendance);
	}
}
