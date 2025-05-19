package com.code_red.phc_attendance_system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BmoDTO extends UserDTO {
	private String blockName;
}
