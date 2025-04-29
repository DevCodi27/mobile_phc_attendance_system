package com.code_red.phc_attendance_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_red.phc_attendance_system.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
