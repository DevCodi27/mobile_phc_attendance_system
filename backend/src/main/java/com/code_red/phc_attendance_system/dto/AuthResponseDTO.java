package com.code_red.phc_attendance_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
	private String token;
	private String role;
	private Long id;
}
