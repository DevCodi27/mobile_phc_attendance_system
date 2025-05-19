package com.code_red.phc_attendance_system.dto;

import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRegistrationDTO {
	private String name;
	private String password;
	private String email;
	private String specialization;
	private Long facility;
	private Role role;
}
