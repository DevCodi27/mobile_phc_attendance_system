package com.code_red.phc_attendance_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.enums.ShiftStatus;
import com.code_red.phc_attendance_system.repositories.ShiftRepository;

@Service
public class ShiftService {

	@Autowired
	private ShiftRepository shiftRepository;

	public List<Shift> getPendingShifts() {
		return shiftRepository.findByStatus(ShiftStatus.PENDING);
	}
}
