package com.code_red.phc_attendance_system.entities;

import jakarta.persistence.Entity;


import java.util.Set;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Dho extends AppUser {
    
    @Column(nullable = false)
    private String district;  // ✅ This must exist

    public Dho() {
        super();
    }

    public Dho(Long userId, String fullName, String email, String password, String phone, String district, Set<Role> roles) {
        super(userId, fullName, email, password,phone, roles);
        this.district = district;
    }

}