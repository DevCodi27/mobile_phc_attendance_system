package com.code_red.phc_attendance_system.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.BmoDTO;
import com.code_red.phc_attendance_system.dto.DhoDTO;
import com.code_red.phc_attendance_system.dto.UserDTO;
import com.code_red.phc_attendance_system.entities.Admin;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Bmo;
import com.code_red.phc_attendance_system.entities.Dho;
import com.code_red.phc_attendance_system.entities.Role;
import com.code_red.phc_attendance_system.repositories.UserRepository;
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public AppUser register(UserDTO userDTO) {
	    if (userDTO == null || userDTO.getRole() == null) {
	        throw new IllegalArgumentException("User data or role cannot be null");
	    }
	    System.out.println("DTO Type: " + userDTO.getClass().getSimpleName());
	    String roleName = userDTO.getRole().getName().toUpperCase();
	    System.out.println("Received Role: " + roleName);

	    AppUser user;

	    if ("BMO".equals(roleName) && userDTO instanceof BmoDTO) {
	        BmoDTO bmoDTO = (BmoDTO) userDTO; // Explicit casting
	        System.out.println("Block Name: " + bmoDTO.getBlockName()); // Debugging log

	        Set<Role> roles = new HashSet<>();
	        roles.add(bmoDTO.getRole());

	        user = new Bmo(
	            bmoDTO.getUserId(),
	            bmoDTO.getFullName(),
	            bmoDTO.getEmail(),
	            passwordEncoder.encode(bmoDTO.getPassword()),
	            bmoDTO.getPhone(),
	            bmoDTO.getBlockName(),
	            roles
	        );
	    } else if ("DHO".equals(roleName) && userDTO instanceof DhoDTO) {
	        DhoDTO dhoDTO = (DhoDTO) userDTO; // Explicit casting
	        System.out.println("District Name: " + dhoDTO.getDistrictName()); // Debugging log

	        Set<Role> roles = new HashSet<>();
	        roles.add(dhoDTO.getRole());

	        user = new Dho(
	            dhoDTO.getUserId(),
	            dhoDTO.getFullName(),
	            dhoDTO.getEmail(),
	            passwordEncoder.encode(dhoDTO.getPassword()),
	            dhoDTO.getPhone(),
	            dhoDTO.getDistrictName(),
	            roles
	        );
	    }
	    else {
	    	Set<Role> roles = new HashSet<>();
	        roles.add(userDTO.getRole());
	        user = new Admin(
	                userDTO.getUserId(),
	                userDTO.getFullName(),
	                userDTO.getEmail(),
	                passwordEncoder.encode(userDTO.getPassword()),
	                userDTO.getPhone(),
	                roles // Assign roles directly
	            );
//	        throw new IllegalArgumentException("User must have either BMO or DHO Received: " + roleName);
	    }

	    return userRepository.save(user);
	}


	public Optional<AppUser> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<AppUser> findDHO(String district){
		return userRepository.findByDistrictAndRole(district);
	}
	
	public AppUser findById(Long id) {
		return userRepository.findById(id).get();
	}
}
