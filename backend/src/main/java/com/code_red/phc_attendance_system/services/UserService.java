package com.code_red.phc_attendance_system.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.UserDTO;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AppUser register(UserDTO userDTO) {
		AppUser user = new AppUser(userDTO.getId(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
		return userRepository.save(user);
	}
	
	public Optional<AppUser> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
