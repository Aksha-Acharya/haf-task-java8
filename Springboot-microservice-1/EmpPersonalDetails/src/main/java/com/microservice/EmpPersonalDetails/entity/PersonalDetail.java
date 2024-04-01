package com.microservice.EmpPersonalDetails.entity;


import java.util.Date;

import com.microservice.EmpPersonalDetails.customValidation.AgeAbove;
import com.microservice.EmpPersonalDetails.customValidation.ValidPhoneNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetail {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
}
