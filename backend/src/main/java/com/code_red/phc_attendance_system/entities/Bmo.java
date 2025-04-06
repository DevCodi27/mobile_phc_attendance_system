package com.code_red.phc_attendance_system.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Bmo extends AppUser {
    
    @Column(nullable = false)
    private String blockName;  // ✅ This must exist

    public Bmo() {
        super();
    }

    public Bmo(Long userId, String fullName, String email, String password, String phone, String blockName, Set<Role> roles) {
        super(userId, fullName, email,  password, phone, roles);
        this.blockName = blockName;
    }
}
