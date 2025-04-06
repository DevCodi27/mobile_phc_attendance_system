package com.code_red.phc_attendance_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

}
