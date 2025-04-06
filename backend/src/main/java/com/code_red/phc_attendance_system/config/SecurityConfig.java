package com.code_red.phc_attendance_system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.code_red.phc_attendance_system.filter.JwtFilter;
import com.code_red.phc_attendance_system.services.CustomUserDetailsService;
import com.code_red.phc_attendance_system.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	        		.cors(cors -> cors.configurationSource(request -> {
	                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
	                    corsConfig.setAllowedOrigins(List.of("http://localhost:5173")); // Allow frontend
	                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	                    corsConfig.setAllowedHeaders(List.of("*"));
	                    corsConfig.setAllowCredentials(true);
	                    return corsConfig;
	                }))
	                .csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(auth -> 
	                			auth	
	                				.requestMatchers("/login").permitAll()
	                				.requestMatchers("/doctors/register").permitAll()
	                				.requestMatchers("/users/register").permitAll()
	                				.anyRequest().authenticated()
	                		)
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .addFilterBefore(new JwtFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }
	    
	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    	provider.setPasswordEncoder(passwordEncoder());
	    	provider.setUserDetailsService(userDetailsService);
	    	return provider;
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

}