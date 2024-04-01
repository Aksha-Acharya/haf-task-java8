package com.employee.EmpData.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Officedata{	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long employeeId;
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
		
	    @OneToOne(mappedBy = "officedata",cascade = CascadeType.ALL)
	    private Personaldata personalDetails;
	  
	 
}
