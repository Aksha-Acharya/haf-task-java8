package com.employee.EmpData.entity.repository.dao;

import com.employee.EmpData.dto.EmpAllDataRequest;
import com.employee.EmpData.entity.Personaldata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonaldataDao {	
	private String name;
    private String dob;
    private String address;
    private Long phoneNo;
    private String emailAddress;
    private double salary;

    public PersonaldataDao(EmpAllDataRequest emp) {
    	this.name=emp.getEmployeeName();
    	this.dob=emp.getDob();
    	this.address=emp.getAddress();
    	this.phoneNo=emp.getPhoneNo();
    	this.emailAddress=emp.getEmailAddress();
    	this.salary=emp.getSalary();
    }
    
    public PersonaldataDao(Personaldata personaldata) {
		this.name=personaldata.getName();
    	this.dob=personaldata.getDob();
    	this.address=personaldata.getAddress();
    	this.phoneNo=personaldata.getPhoneNo();
    	this.emailAddress=personaldata.getEmailAddress();
    	this.salary=personaldata.getSalary();

}

	@Override
	public String toString() {
		return "name=" + name + ", dob=" + dob + ", address=" + address + ", phoneNo=" + phoneNo
				+ ", emailAddress=" + emailAddress + ", salary=" + salary ;
	}
    
    

}
