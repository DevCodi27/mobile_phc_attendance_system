package com.code_red.phc_attendance_system.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.code_red.phc_attendance_system.enums.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	private LocalTime checkInTime;
	private Integer missedCounts;
	private LocalTime checkOutTime;
	private LocalDate date;
	private AttendanceStatus status;
}
