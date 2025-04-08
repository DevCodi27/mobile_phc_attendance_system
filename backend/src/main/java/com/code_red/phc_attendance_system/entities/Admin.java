package com.code_red.phc_attendance_system.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Admin extends AppUser {
	public Admin(Long userId, String fullName, String email, String password, String phone, Set<Role> roles) {
        super(userId, fullName, email, password, phone, roles);
    }
}
