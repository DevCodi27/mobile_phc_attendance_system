package com.code_red.phc_attendance_system.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.dto.FingerprintDTO;
import com.code_red.phc_attendance_system.dto.ShiftDTO;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;
import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.enums.ShiftStatus;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;
import com.code_red.phc_attendance_system.repositories.ShiftRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Doctor register(DoctorDTO doctorDTO) {
		Set<Role> roles = new HashSet<>();
        roles.add(doctorDTO.getRole());
		Doctor doctor = new Doctor();
		doctor.setDoctorId(doctorDTO.getDoctorId());
	    doctor.setFullName(doctorDTO.getName());
	    doctor.setEmail(doctorDTO.getEmail());
	    doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword())); // Encrypt password
	    doctor.setSpecialization(doctorDTO.getSpecialization());
	    doctor.setFacility(doctorDTO.getFacility());
	    doctor.setRoles(roles);
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
    
	public List<DoctorDTO> getByFacility(Facility facility) {
        // Fetch list of doctors and map to DTOs
        return doctorRepository.findByFacility(facility)
                .stream()
                .map(DoctorDTO::new)  // Convert Doctor to DoctorDTO
                .collect(Collectors.toList());
    }

	public Optional<Doctor> findByEmail(String email) {
		// TODO Auto-generated method stub
		return doctorRepository.findByEmail(email);
	}
	
	public Doctor getDoctorById(Long id) {
		
		return doctorRepository.findById(id).get();
	}
	
	 @Transactional
	    public Doctor updateDoctorShift(Long doctorId, ShiftDTO newShift) {
		 Doctor doctor = getDoctorById(doctorId);
		    AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		    // Allow BMO to edit shift
		    if (user.getRoles().contains("BMO")) {
		        Shift shift = new Shift();
		        shift.setDate(newShift.getDate());
		        shift.setStartTime(newShift.getStartTime());
		        shift.setEndTime(newShift.getEndTime());
		        shift.setStatus(ShiftStatus.PENDING);
		        shift.setAssignedBy(user); // Track who assigned it
		        Shift savedShift = shiftRepository.save(shift);
		        doctor.setShift(savedShift);
		    }

		    // Allow DHO to verify shift
		    if (user.getRoles().contains("DHO")) {
		        if (doctor.getShift() != null) {
		            doctor.getShift().setStatus(ShiftStatus.APPROVED);
		            doctor.getShift().setApprovedBy(user);
		            shiftRepository.save(doctor.getShift());
		        }
		    }
		    
		    return doctorRepository.save(doctor);
		   
	    }

	   
	    public List<FingerprintDTO> getIdAndFingerprintByFacility(Facility facility) {
	        List<Doctor> doctors = doctorRepository.findByFacility(facility);

	        return doctors.stream()
	                .map(doctor -> {
	                    FingerprintDTO dto = new FingerprintDTO();
	                    dto.setId(doctor.getDoctorId());
	                    dto.setFingerprint(doctor.getFingerprint());
	                    return dto;
	                })
	                .collect(Collectors.toList());
	    }
}
