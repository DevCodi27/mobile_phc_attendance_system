package com.code_red.phc_attendance_system.entities;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "shifts")
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private LocalTime startTime;
	private LocalTime endTime;
	@ManyToOne
	@JoinColumn(name = "assigned_by")
	private AppUser assignedBy;
	@ManyToOne
	@JoinColumn(name = "approved_by")
	private AppUser approvedBy;
	
	private String status;
}
