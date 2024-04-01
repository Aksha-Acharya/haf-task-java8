package com.microservice.EmpOfficeDetails.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmpOfficeDetails.Exception.NoSuchEmployeeFoundException;
import com.microservice.EmpOfficeDetails.request.EmployeeAllDataRequest;
import com.microservice.EmpOfficeDetails.response.EmpOfficeDetailResponse;
import com.microservice.EmpOfficeDetails.response.PersonalDetailResponse;
import com.microservice.EmpOfficeDetails.service.OfficeDetailService;

import jakarta.validation.Valid;

@RestController
public class OfficeDetailController {

	@Autowired
	OfficeDetailService officeDetailService;

	@PostMapping("/employees")
	public ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeAllDataRequest empOffc, BindingResult result) throws AccessDeniedException {

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(officeDetailService.addEmployeeDetails(empOffc));
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<EmpOfficeDetailResponse> getEmpDetail(@PathVariable("employeeId") Long empId)
			throws NoSuchEmployeeFoundException,AccessDeniedException {
			//
		EmpOfficeDetailResponse officeDetail = officeDetailService.getAllDetail(empId);
		return ResponseEntity.ok(officeDetail);
	}

	@GetMapping("/employees/personal/{employeeId}")
	public ResponseEntity<PersonalDetailResponse> getPersonalDetail(
			 @PathVariable("employeeId") Long empId) throws NoSuchEmployeeFoundException,AccessDeniedException {
	//
		PersonalDetailResponse personalDetailResponse = officeDetailService.getPersonalDetailResponse(empId);
		return ResponseEntity.ok(personalDetailResponse);
	}

	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<?> updateEmployeeDetail(@Valid @RequestBody EmployeeAllDataRequest updateDetail,
			  @PathVariable("employeeId") Long empId, BindingResult result) throws NoSuchEmployeeFoundException,AccessDeniedException {
		if (result.hasErrors()) {
			// If there are validation errors, return a response with the validation errors
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(officeDetailService.updateEmployeeDetails(updateDetail, empId));
	}


	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployeeDetails(@PathVariable("employeeId") Long empId)
			throws NoSuchEmployeeFoundException,AccessDeniedException {
		officeDetailService.deleteEmployeeDetails(empId);
		return "Employee detail deleted completely";
	}

}
