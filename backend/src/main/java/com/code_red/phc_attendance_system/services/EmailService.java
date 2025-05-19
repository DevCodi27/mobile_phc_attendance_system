package com.code_red.phc_attendance_system.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.entities.Facility;

@Service
public class EmailService {

	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired 
	private UserService userService;

	public void sendAlert(Long id, String name, String dhoMail, Facility facility) {
		String to = dhoMail;
		String subject = "Absence alert";
		String body = "The doctor id: " + id + " name: " + name + " is absent at " + LocalDateTime.now() + " in "
				+ facility.getName() + ", " + facility.getBlock() + ", " + facility.getDistrict();

		emailService.sendEmail(to, subject, body);
	}

	public void sendRegisterNotification(String name, Long id, String toEmail, String password) {
		String subject = "DDHS - User Registration";
		String body = "Mr./Ms." + name +",\n The doctor id: "+id +" name: "+ name + ".\n Welcome to DDHS!\n Your login credentials:\n email: "+ toEmail + "\npassword: "+ password;
		emailService.sendEmail(toEmail, subject, body);
		
	}
	@Scheduled(cron = "0 0 0 * * *")
	public void sendAttendaceReport(List<Attendance> attendance) {
		String subject = "Attendance Report" + LocalDate.now();
		if(!(attendance.size()>0)) {
			return;
		}
		Optional<AppUser> dho = userService.findDHO(attendance.get(0).getDoctor().getFacility().getDistrict());
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("<h3>Attendance Report - ").append(LocalDate.now()).append("</h3>");
		bodyBuilder.append("<table border='1' cellpadding='5' cellspacing='0'>");
		bodyBuilder.append("<tr>")
		           .append("<th>ID</th>")
		           .append("<th>Doctor Name</th>")
		           .append("<th>Facility</th>")
		           .append("<th>Date</th>")
		           .append("<th>Check-in</th>")
		           .append("<th>Check-out</th>")
		           .append("<th>Status</th>")
		           .append("<th>Missed Count</th>")
		           .append("</tr>");

		for (Attendance a : attendance) {
		    bodyBuilder.append("<tr>")
		               .append("<td>").append(a.getId()).append("</td>")
		               .append("<td>").append(a.getDoctor().getFullName()).append("</td>")
		               .append("<td>").append(a.getDoctor().getFacility().getName()).append("</td>")
		               .append("<td>").append(a.getDate()).append("</td>")
		               .append("<td>").append(a.getCheckInTime()).append("</td>")
		               .append("<td>").append(a.getCheckOutTime()).append("</td>")
		               .append("<td>").append(a.getStatus()).append("</td>")
		               .append("<td>").append(a.getMissedCounts()).append("</td>")
		               .append("</tr>");
		}
		bodyBuilder.append("</table>");

		String body = bodyBuilder.toString();

		if(!dho.isPresent()) {
		  String dhoEmail =dho.get().getEmail();
		  emailService.sendEmail(dhoEmail, subject, body);
		}
	}

}
