package com.code_red.phc_attendance_system.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Facility;

@Service
public class EmailService {

	@Autowired
	private EmailServiceImpl emailService;

	@Scheduled(cron = "0 0 0 * * *")
	public void sendAttendanceToDHO() {

	}

	public void sendAlert(Long id, String name, String dhoMail, Facility facility) {
		String to = dhoMail;
		String subject = "Absence alert";
		String body = "The doctor id: " + id + " name: " + name + " is absent at " + LocalDateTime.now() + " in "
				+ facility.getName() + ", " + facility.getBlock() + ", " + facility.getDistrict();

		emailService.sendEmail(to, subject, body);
	}

	public void sendRegisterNotification(String name, Long id, String toEmail, String password) {
		String subject = "DDHS - User Registration";
		String body = "Mr./Ms."  name +",\n The doctor id: "+id +" name: "+ name + ".\n Welcome to DDHS!\n Your login credentials:\n email: "+ toEmail + "\npassword: "+ password;
		emailService.sendEmail(toEmail, subject, body);
		
	}

}
