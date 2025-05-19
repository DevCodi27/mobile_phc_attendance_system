package com.code_red.phc_attendance_system.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToMany
	private Set<AppUser> users;

	@ManyToMany
	private Set<Doctor> doctors;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}
}
