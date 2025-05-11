package com.code_red.phc_attendance_system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.enums.ShiftStatus;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
	List<Shift> findByStatus(ShiftStatus status);
}
