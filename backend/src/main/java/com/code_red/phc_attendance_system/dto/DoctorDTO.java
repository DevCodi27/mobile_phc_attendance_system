package com.code_red.phc_attendance_system.dto;

import java.util.Set;

import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long doctorId;
    private String name;
    private String password;
    private String email;
    private String specialization;
    private Facility facility;
    private Set<Role> roles;
    
    public DoctorDTO(Doctor doctor) {
    	this.doctorId = doctor.getDoctorId();
    	this.email = doctor.getEmail();
    	this.name = doctor.getFullName();
    	this.password = doctor.getPassword();
    	this.specialization = doctor.getSpecialization();
    	this.facility = doctor.getFacility();
    	this.roles = doctor.getRoles();
    }
}
