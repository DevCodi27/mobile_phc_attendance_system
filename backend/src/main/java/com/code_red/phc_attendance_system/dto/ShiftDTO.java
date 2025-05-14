package com.code_red.phc_attendance_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.code_red.phc_attendance_system.entities.Bmo;
import com.code_red.phc_attendance_system.entities.Dho;
import com.code_red.phc_attendance_system.enums.ShiftStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShiftDTO {
	private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long assignedBy;
    private Long approvedBy;
    
    
    private ShiftStatus status;

	public ShiftDTO() {}

}
