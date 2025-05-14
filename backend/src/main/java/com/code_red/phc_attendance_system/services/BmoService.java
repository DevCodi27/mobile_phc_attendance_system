package com.code_red.phc_attendance_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Bmo;
import com.code_red.phc_attendance_system.repositories.BmoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BmoService {

	@Autowired
	private BmoRepository bmoRepository;

	public Bmo getBmoByBlockName(String blockName) {
		return bmoRepository.findByBlockName(blockName)
				.orElseThrow(() -> new EntityNotFoundException("BMO not found with block name: " + blockName));
	}

	public Bmo findById(Long id) {
		return bmoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("BMO not found with id: " + id));
	}
}
