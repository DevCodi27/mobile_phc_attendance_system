package com.code_red.phc_attendance_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.entities.Bmo;
import com.code_red.phc_attendance_system.services.BmoService;

@RestController
@RequestMapping("/api/bmo")
public class BmoController {

    @Autowired
    private BmoService bmoService;

    @GetMapping
    public ResponseEntity<Bmo> getBmoByBlockName(@RequestParam String blockName) {
        Bmo bmo = bmoService.getBmoByBlockName(blockName);
        return new ResponseEntity<>(bmo, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bmo> getBmoById(@PathVariable Long id) {
        Bmo bmo = bmoService.findById(id);
        return new ResponseEntity<>(bmo, HttpStatus.ACCEPTED);
    }
 
}
