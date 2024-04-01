package com.microservice.EmpOfficeDetails.response;

import java.util.Date;

import com.microservice.EmpOfficeDetails.entity.OfficeDetail;

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
public class EmpOfficeDetailResponse {
	@NotNull
	private Long employeeId;
	
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
    
    private PersonalDetailResponse personalDetailResponse;    
    
    public EmpOfficeDetailResponse(OfficeDetail officeDetail){
    	this.employeeId=officeDetail.getEmployeeId();
    	this.employeeName=officeDetail.getEmployeeName();
    	this.dateOfJoining=officeDetail.getDateOfJoining();
    	this.designation=officeDetail.getDesignation();
    	this.projectName=officeDetail.getProjectName();
    	this.employeeType=officeDetail.getEmployeeType();
    	
    }

	

	
}
