package com.code_red.phc_attendance_system.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;
import com.code_red.phc_attendance_system.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.code_red.phc_attendance_system.entities.AppUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<AppUser> user = userRepository.findByEmail(email);
		Optional<Doctor> doctor = doctorRepository.findByEmail(email);

		if (user.isPresent()) {
			return new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
		} else if (doctor.isPresent()) {
			return new User(doctor.get().getEmail(), doctor.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}
}
