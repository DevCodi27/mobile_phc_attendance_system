package com.code_red.phc_attendance_system.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor {

	    @Id
	    private Long doctorId;
	    
	    private String fullName;

	    @Column(unique = true, nullable = false)
	    private String email;

	    private String password;

	    private String specialization;
	    @ManyToOne
	    @JoinColumn(name = "facility_id")
	    @JsonIgnore
	    private Facility facility;
	    
	    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(
	        name = "doctor_roles",
	        joinColumns = @JoinColumn(name = "doctor_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles;
	    
	    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
	    private Shift shift;
	    public Doctor() { }
}
