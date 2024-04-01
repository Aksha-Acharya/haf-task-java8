package com.microservice.EmpOfficeDetails.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OfficeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    

}
