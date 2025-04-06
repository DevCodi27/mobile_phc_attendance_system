package com.code_red.phc_attendance_system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DhoDTO extends UserDTO {
    private String districtName;
}
