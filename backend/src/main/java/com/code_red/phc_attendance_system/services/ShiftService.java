package com.code_red.phc_attendance_system.services;

import java.util.List;
import java.util.Optional;

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

	public boolean updateShift(String status, Long id) {
		Optional<Shift> shift = shiftRepository.findById(id);
		if(!shift.isPresent()) throw new RuntimeException("No shift find by id" + id);
		
		Shift sh = shift.get();
		if(status.equals("ACCEPTED")) {
			sh.setStatus(ShiftStatus.APPROVED);
		}
		else sh.setStatus(ShiftStatus.REJECTED);
		
		shiftRepository.save(sh);
		return true;
		
		
	}
	
}
