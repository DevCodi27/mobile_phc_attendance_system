package com.code_red.phc_attendance_system.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.dto.DoctorRegistrationDTO;
import com.code_red.phc_attendance_system.dto.FingerprintDTO;
import com.code_red.phc_attendance_system.dto.ShiftDTO;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Bmo;
import com.code_red.phc_attendance_system.entities.Dho;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;
import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.enums.ShiftStatus;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;
import com.code_red.phc_attendance_system.repositories.RoleRepository;
import com.code_red.phc_attendance_system.repositories.ShiftRepository;

import io.jsonwebtoken.lang.Collections;
import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;
	
	public Doctor register(DoctorRegistrationDTO doctorDTO) {
		Role role = roleRepository.findById(doctorDTO.getRole().getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
		Set<Role> roles = new HashSet<>();
        roles.add(role);
		Doctor doctor = new Doctor();
		Facility facility = facilityService.findById(doctorDTO.getFacility());
	    doctor.setFullName(doctorDTO.getName());
	    doctor.setEmail(doctorDTO.getEmail());
	    doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword())); // Encrypt password
	    doctor.setSpecialization(doctorDTO.getSpecialization());
	    doctor.setFacility(facility);
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

	    Bmo assignedBy = (Bmo)userService.findById(newShift.getAssignedBy());

	    Dho approvedBy = null;
	    if (newShift.getApprovedBy() != null) {
	        approvedBy = (Dho)userService.findById(newShift.getApprovedBy());
	    }

	    Shift shift = new Shift();
	    shift.setDate(newShift.getDate());
	    shift.setStartTime(newShift.getStartTime());
	    shift.setEndTime(newShift.getEndTime());
	    shift.setStatus(ShiftStatus.PENDING);
	    shift.setAssignedBy(assignedBy);
	    shift.setApprovedBy(approvedBy);

	    Shift savedShift = shiftRepository.save(shift);
	    doctor.setShift(savedShift);

	    return doctorRepository.save(doctor);
	}
	
    public Map<String, List<DoctorDTO>> getDoctorsGroupedByBlock(String blockName) {
        List<Facility> facilities = facilityService.findByBlock(blockName);

        if (facilities == null || facilities.isEmpty()) {
            return Map.of(blockName, Collections.emptyList());
        }

        List<Doctor> doctors = doctorRepository.findByFacilityIn(facilities);
        
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorDTO dto = new DoctorDTO(doctor);
            doctorDTOs.add(dto);
        }

        Map<String, List<DoctorDTO>> result = new HashMap<>();
        result.put(blockName, doctorDTOs);
        return result;
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
