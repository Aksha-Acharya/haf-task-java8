package com.employee.EmpData.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.EmpData.entity.UserAccess;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
	
	Optional<UserAccess> findByUsername(String username);
	
}
