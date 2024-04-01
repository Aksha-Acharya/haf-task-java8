package com.microservice.EmpPersonalDetails.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmpPersonalDetails.request.PersonalDetailRequest;
import com.microservice.EmpPersonalDetails.response.PersonalDetailResponse;
import com.microservice.EmpPersonalDetails.service.PersonalDetailService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class PersonalDetailController {

	@Autowired
	PersonalDetailService personalDetailService;

	@PostMapping("/employees/personal")
	public ResponseEntity<?> addEmployee(@RequestBody @Valid PersonalDetailRequest personalDetailRequest, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(personalDetailService.addEmployeeDetails(personalDetailRequest));
	}
	
	@PutMapping("/employees/personal/{empId}")
	public ResponseEntity<?> updatePersonaDetail(@RequestBody @Valid PersonalDetailRequest personalDetail,@PathVariable ("empId") @NotNull Long empId
			,BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}		
		personalDetailService.updatePersonDetail(personalDetail, empId);
		return ResponseEntity.ok("employee personal detail updated successfully.");
	}

	@GetMapping("/employees/personal/{empId}")
	public PersonalDetailResponse getPersonalDetail(@PathVariable("empId") @NotNull Long empId) {
		return personalDetailService.getPersonDetail(empId);
	}
	
	@DeleteMapping("/employees/personal/{empId}")
	public ResponseEntity<String> deletePersonalDetail(@PathVariable("empId") @NotNull Long empId){		
		String response=personalDetailService.deleteEmployeePersonaldetail(empId);
		return ResponseEntity.ok(response);				
	}

}
