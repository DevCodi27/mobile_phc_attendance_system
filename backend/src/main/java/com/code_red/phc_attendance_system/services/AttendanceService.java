package com.code_red.phc_attendance_system.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.entities.Dho;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.enums.AttendanceStatus;
import com.code_red.phc_attendance_system.repositories.AttendanceRepository;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;
import com.code_red.phc_attendance_system.repositories.FacilityRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;
	
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
    
    public Map<Facility, List<Attendance>> getAttendanceOfDoctors() {
        Dho user = (Dho)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String district = user.getDistrict();
        LocalDate today = LocalDate.now();
        List<Facility> facilities = facilityRepository.findByDistrict(district);
        Map<Facility, List<Attendance>> attendance = new HashMap<>();
        LocalDate date = LocalDate.now();
        for(Facility facility: facilities) {
        	List<Doctor> doctors = doctorRepository.findByFacility(facility);
        	List<Attendance> attendances = new ArrayList<>();
        	for(Doctor doctor: doctors) {
        		Attendance att = attendanceRepository.findByDoctorAndDate(doctor, date);
        		attendances.add(att);
        	}
        	attendance.put(facility, attendances);
        }

        return attendance;
    }
}
