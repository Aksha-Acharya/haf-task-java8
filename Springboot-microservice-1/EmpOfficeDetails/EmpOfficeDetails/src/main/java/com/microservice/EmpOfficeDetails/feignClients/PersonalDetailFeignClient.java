package com.microservice.EmpOfficeDetails.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.EmpOfficeDetails.response.PersonalDetailResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@FeignClient(url="${personaldetail.service.url}", value = "personal-feign-client")
public interface PersonalDetailFeignClient {

	@GetMapping("/employees/personal/{empId}")
	public PersonalDetailResponse getPersonalDetail(@PathVariable("empId") @NotNull Long empId);
				
	@DeleteMapping("/employees/personal/{empId}")
	public ResponseEntity<String> deletePersonalDetail(@PathVariable("empId") @NotNull Long empId);
					
	
	@PostMapping("/employees/personal")
	public ResponseEntity<?> addEmployee(@RequestBody @Valid PersonalDetailResponse personalDetailRequest, BindingResult result);
}
