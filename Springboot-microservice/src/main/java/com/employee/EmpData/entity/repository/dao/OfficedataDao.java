package com.employee.EmpData.entity.repository.dao;
import com.employee.EmpData.dto.EmpAllDataRequest;
import com.employee.EmpData.entity.Officedata;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OfficedataDao {	
	  	private String employeeName;
	    private String dateOfJoining;
	    private String designation;
	    private String projectName;
	    private String employeeType;
	   // private PersonaldataDao personalDetails;
	    
	   public OfficedataDao(EmpAllDataRequest emp) {
		   this.employeeName=emp.getEmployeeName();
		   this.dateOfJoining=emp.getDateOfJoining();
		   this.designation=emp.getDesignation();
		   this.projectName=emp.getProjectName();
		   this.employeeType=emp.getEmployeeType();
		   
	   }

	public OfficedataDao(Officedata officedata) {
		 this.employeeName=officedata.getEmployeeName();
		   this.dateOfJoining=officedata.getDateOfJoining();
		   this.designation=officedata.getDesignation();
		   this.projectName=officedata.getProjectName();
		   this.employeeType=officedata.getEmployeeType();	
	}

	@Override
	public String toString() {
		return "employeeName=" + employeeName + ", dateOfJoining=" + dateOfJoining + ", designation="
				+ designation + ", projectName=" + projectName + ", employeeType=" + employeeType ;
	}
	
	
	
}
