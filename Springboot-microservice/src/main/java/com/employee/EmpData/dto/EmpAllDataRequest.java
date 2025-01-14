package com.employee.EmpData.dto;

import com.employee.EmpData.customValidation.AgeAbove;
import com.employee.EmpData.customValidation.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpAllDataRequest {
	@NotBlank(message = "Employee name is required")
    private String employeeName;
	@NotNull(message = "Date of joining is required")
    private String dateOfJoining;
	@NotBlank(message = "Designation is required")
    private String designation;
	@NotBlank(message = "Project name is required")
    private String projectName;
	@NotBlank(message = "Employee type is required")
    @Pattern(regexp = "Contract|Permanent",
    message = "Employee type must be 'Contract' or 'Permanent'")
    private String employeeType;
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
	
	private String username;
	private String password;
	private String roles;

}
