package com.code_red.phc_attendance_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Bmo;

@Repository
public interface BmoRepository extends JpaRepository<Bmo, Long> {
    Optional<Bmo> findByBlockName(String blockName);
}

