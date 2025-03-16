package com.code_red.phc_attendance_system.dto;

import com.code_red.phc_attendance_system.entities.Role;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import java.util.Set;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "userType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = BmoDTO.class, name = "BMO"),
    @JsonSubTypes.Type(value = DhoDTO.class, name = "DHO")
})
public class UserDTO {
    private Long userId;
    private String fullName;
    private String email;
    private String phone;
    private String password; // Needed for registration
    private Role role;
}
