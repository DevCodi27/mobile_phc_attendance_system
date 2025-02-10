package com.code_red.phc_attendance_system.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "users")
public class AppUser {
	    @Id
	    private Long userId;
	    private String fullName;
	    
	    @Column(unique = true, nullable = false)
	    private String email;
	    
	    private String password;
	    private String phone;
	    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Facility facility;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles;
	    
	    public AppUser() {}
	    
	    public AppUser(Long id, String email, String password) {
		  this.userId = id;
		  this.email = email;
		  this.password = password;
	  }
	   
}
