package com.employee.EmpData.entity;

import com.employee.EmpData.customValidation.AgeAbove;
import com.employee.EmpData.customValidation.ValidPhoneNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Personaldata {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotBlank(message = "Personal name is required")
		private String name;
		@NotNull(message = "Date of birth is required")
	    @AgeAbove(message = "Age must be above 18")
	    private String dob;
		@NotBlank(message = "Address is required")
	    private String address;
		@NotNull(message = "Phone number is required")
	    @ValidPhoneNumber
	    private Long phoneNo;
		@NotBlank(message = "Email is required")
		@Email(message = "Email should be valid")
		@Pattern(regexp = ".+@.+\\..+", message = "Email should be valid")
	    private String emailAddress;
		@NotNull(message = "Salary is required")
	    private double salary;

	    @OneToOne
	    @JoinColumn(name = "employeeId")
	    private Officedata officedata;
	    
	    	    
}
