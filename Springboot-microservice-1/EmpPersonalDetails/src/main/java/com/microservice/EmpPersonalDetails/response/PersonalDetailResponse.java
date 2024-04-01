package com.microservice.EmpPersonalDetails.response;


import java.util.Date;

import com.microservice.EmpPersonalDetails.customValidation.AgeAbove;
import com.microservice.EmpPersonalDetails.customValidation.ValidPhoneNumber;
import com.microservice.EmpPersonalDetails.entity.PersonalDetail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalDetailResponse {
	
			@NotNull(message = "Personal ID is required")
			private Long personalId;
			@NotBlank(message = "Employee name is required")
			private String name;
		
			@NotNull(message = "Date of birth is required")
			@AgeAbove(message = "Age must be above 18")
			private Date dob;
		    
		    @NotBlank(message = "Address is required")
			private String address;
		    
			@NotNull(message = "Phone number is required")
		    @ValidPhoneNumber
			private Long phoneNo;
			
			@NotBlank(message = "Email is required")
		    @Email(message = "Email should be valid")
			private String email;
			
			@NotBlank(message = "Salary is required")
			private String salary;
	
	public PersonalDetailResponse(PersonalDetail personalDetail) {
		this.personalId=personalDetail.getPersonalId();
		this.name=personalDetail.getName();
		this.dob=personalDetail.getDob();
		this.address=personalDetail.getAddress();
		this.phoneNo=personalDetail.getPhoneNo();
		this.email=personalDetail.getEmail();
		this.salary=personalDetail.getSalary();		
	}


}
