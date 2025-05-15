package com.code_red.phc_attendance_system.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.enums.AttendanceStatus;
import com.code_red.phc_attendance_system.repositories.AttendanceRepository;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	public Attendance markAttendance(Long id) {
		Attendance attendance = new Attendance();
		attendance.setStatus(AttendanceStatus.PRESENT);
		attendance.setDoctor(doctorRepository.findById(id).get());
		attendance.setCheckInTime(LocalTime.now());
		attendance.setDate(LocalDate.now());
		return attendanceRepository.save(attendance);
	}

	@Scheduled(cron = "0 0 10 * * *")
	public void markAbsentDoctors() {
		LocalDate today = LocalDate.now();

		List<Doctor> allDoctors = doctorRepository.findAll();
		for (Doctor doctor : allDoctors) {
			boolean hasAttendance = attendanceRepository.existsByDoctorAndDate(doctor, today);
			if (!hasAttendance) {
				Attendance attendance = new Attendance();
				attendance.setDoctor(doctor);
				attendance.setDate(today);
				attendance.setStatus(AttendanceStatus.ABSENT);
				attendanceRepository.save(attendance);
			}
		}
		System.out.println("Absent doctors marked at 10 AM.");
	}

		public List<Attendance> findByToday(){
			return attendanceRepository.findByDate(LocalDate.now());
		}
}
