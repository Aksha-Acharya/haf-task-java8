package com.employee.EmpData.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.EmpData.dto.EmpAllDataRequest;
import com.employee.EmpData.entity.repository.dao.EmpAllDataResponse;
import com.employee.EmpData.entity.repository.dao.PersonaldataDao;
import com.employee.EmpData.exception.EmployeeNotFoundException;
import com.employee.EmpData.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Tag(name = "EmployeeController", description = "To perform operations on Employee office data and personal data")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Operation(
			summary = "POST operation on Employee data",
			description = "It is used to save Employee data into database out of which office data"
					+ "will saved into office data table and personal data will be saved into personal data table"
			)
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid EmpAllDataRequest employee, BindingResult error) {
		if (error.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			error.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		String result = employeeService.createEmployee(employee);
		return ResponseEntity.ok(result);
	}

	@Operation(
			summary = "GET operation on Employee data",
			description = "It is used to return Employee data saved in the database. It displays both office data and personal data"					
			)
	
	@GetMapping("/employees/{employeeId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public EmpAllDataResponse getEmployee(@PathVariable @NotNull Long employeeId)
			throws EmployeeNotFoundException {
		EmpAllDataResponse emp=employeeService.getEmployee(employeeId);
		return emp;
	}

	@Operation(
			summary = "GET operation on Employee data",
			description = "It is used to return Employee Personal data saved in the database. It displays only personal data of the Employee"					
			)
	@GetMapping("/employees/personal/{employeeId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP','ROLE_MANAGER')")
	public ResponseEntity<PersonaldataDao> getPersonalDetails(@PathVariable @NotNull Long employeeId)
			throws EmployeeNotFoundException {
		return ResponseEntity.ok(employeeService.getPersonalDetails(employeeId));
	}

	@Operation(
			summary = "PUT operation on Employee data",
			description = "It is used to update the Employee data saved in the database. It updates both personal data and officedata of the Employee"					
			)
	@PutMapping("/employees/{employeeId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> updateEmployee(@PathVariable @NotNull Long employeeId,
			@RequestBody @Valid EmpAllDataRequest employee, BindingResult result) throws EmployeeNotFoundException {
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employee));
	}

	@Operation(
			summary = "DELETE operation on Employee data",
			description = "It is used to delete the Employee data saved in the database. It deletes both personal data and office data of the Employee"					
			)
	@DeleteMapping("/employees/{employeeId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEmployee(@PathVariable @NotNull Long employeeId)
			throws EmployeeNotFoundException {
		return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
	}

}
