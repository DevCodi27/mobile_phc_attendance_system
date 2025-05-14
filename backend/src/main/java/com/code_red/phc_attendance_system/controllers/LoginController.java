package com.code_red.phc_attendance_system.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.dto.AuthResponseDTO;
import com.code_red.phc_attendance_system.dto.LoginDTO;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Role;
import com.code_red.phc_attendance_system.services.CustomUserDetailsService;
import com.code_red.phc_attendance_system.services.DoctorService;
import com.code_red.phc_attendance_system.services.UserService;
import com.code_red.phc_attendance_system.util.JwtUtil;


@RestController
@RequestMapping
public class LoginController {
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private DoctorService doctorService;
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
	        try {
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	            );
	            
	            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		        String email = userDetails.getUsername();
		        Optional<AppUser> user = userService.findByEmail(email);
		        Optional<Doctor> doctor = doctorService.findByEmail(email);
		        Set<Role> roles;
		        Long id;
		        if (user.isPresent()) {
		            roles = user.get().getRoles();
		            id = user.get().getUserId();
		        } else if (doctor.isPresent()) {
		            roles = doctor.get().getRoles();
		            id = doctor.get().getDoctorId();
		        } else {
		            throw new BadCredentialsException("User not found");
		        }
		        
		        List<String> roleNames = roles.stream()
		                .map(Role::getName)
		                .collect(Collectors.toList());
		        
		        final String token = jwtUtil.generateToken(userDetails, roles);
		        return ResponseEntity.ok(new AuthResponseDTO(token, roleNames.get(0), id));
	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }
}
