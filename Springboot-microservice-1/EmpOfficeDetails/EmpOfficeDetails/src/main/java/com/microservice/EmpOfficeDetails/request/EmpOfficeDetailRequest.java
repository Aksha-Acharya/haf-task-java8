package com.microservice.EmpOfficeDetails.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmpOfficeDetailRequest {
	
	@NotBlank(message = "Employee name is required")
    private String employeeName;
	
	@NotNull(message = "Date of joining is required")
    private Date dateOfJoining;
	
    @NotBlank(message = "Designation is required")
    private String designation;
    
    @NotBlank(message = "Project name is required")
    private String projectName;
    
    @NotBlank(message = "Employee type is required")
    @Pattern(regexp = "Contract|Permanent",
    message = "Employee type must be 'Contract' or 'Permanent'")
    private String employeeType;
//    
//    @NotNull(message = "Personal ID is required")
//    private Long personalId;
//    
    public EmpOfficeDetailRequest(EmployeeAllDataRequest employeeAllDataRequest){
    	
    	this.employeeName=employeeAllDataRequest.getEmployeeName();
    	this.dateOfJoining=employeeAllDataRequest.getDateOfJoining();
    	this.designation=employeeAllDataRequest.getDesignation();
    	this.projectName=employeeAllDataRequest.getProjectName();
    	this.employeeType=employeeAllDataRequest.getEmployeeType();
    //	this.personalId=employeeAllDataRequest.getPersonalId();
    } 

}
