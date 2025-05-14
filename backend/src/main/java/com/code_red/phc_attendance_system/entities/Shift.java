package com.code_red.phc_attendance_system.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.code_red.phc_attendance_system.enums.ShiftStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shifts")
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	@ManyToOne
	@JoinColumn(name = "assigned_by")
	private Bmo assignedBy;
	@ManyToOne
	@JoinColumn(name = "approved_by")
	private Dho approvedBy;
	
	@Enumerated(EnumType.STRING)
	private ShiftStatus status;

}
