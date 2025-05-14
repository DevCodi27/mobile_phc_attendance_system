package com.code_red.phc_attendance_system.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // Creates separate tables
public abstract class AppUser {

	@Id
	private Long userId;
	private String fullName;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	private String phone;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public AppUser() {
	}

	public AppUser(Long id, String email, String password, Set<Role> role) {
		this.userId = id;
		this.email = email;
		this.password = password;
		this.roles = role; // ✅ Initialize Set
	}

}
