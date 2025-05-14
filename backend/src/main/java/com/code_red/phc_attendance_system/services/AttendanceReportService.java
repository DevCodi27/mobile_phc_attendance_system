package com.code_red.phc_attendance_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.repositories.AttendanceRepository;
import com.code_red.phc_attendance_system.util.PdfGenerator;

@Service
public class AttendanceReportService {

	@Autowired
	private AttendanceService attendanceService;

	public byte[] generateReportForDate(LocalDate date) {
//        List<Attendance> records = attendanceService.getAttendanceOfDoctors()
//        return PdfGenerator.generateAttendanceReport(records, date);
		return null;
	}
}
