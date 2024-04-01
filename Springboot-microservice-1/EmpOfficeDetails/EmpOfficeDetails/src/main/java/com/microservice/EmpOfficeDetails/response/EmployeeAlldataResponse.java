package com.microservice.EmpOfficeDetails.response;

import com.microservice.EmpOfficeDetails.request.EmpOfficeDetailRequest;
import com.microservice.EmpOfficeDetails.request.PersonalDetailRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeeAlldataResponse {

	EmpOfficeDetailRequest empOfficeDetailRequest;
	PersonalDetailRequest personalDetailRequest;

	public EmployeeAlldataResponse(EmpOfficeDetailRequest empOfficeDetailRequest,
			PersonalDetailRequest personalDetailRequest) {
		
		this.empOfficeDetailRequest = empOfficeDetailRequest;
		this.personalDetailRequest = personalDetailRequest;
	}

	@Override
	public String toString() {
		return "EmployeeAlldataResponse [empOfficeDetailRequest=" + empOfficeDetailRequest + ", personalDetailRequest="
				+ personalDetailRequest + "]"+ "your employee ID is";
	}
	
	
}
