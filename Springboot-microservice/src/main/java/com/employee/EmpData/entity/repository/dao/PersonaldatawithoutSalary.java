package com.employee.EmpData.entity.repository.dao;

import com.employee.EmpData.entity.Personaldata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonaldatawithoutSalary {
	private String name;
    private String dob;
    private String address;
    private Long phoneNo;
    private String emailAddress;
    
    public PersonaldatawithoutSalary(Personaldata personaldata) {
		this.name=personaldata.getName();
    	this.dob=personaldata.getDob();
    	this.address=personaldata.getAddress();
    	this.phoneNo=personaldata.getPhoneNo();
    	this.emailAddress=personaldata.getEmailAddress();

}

	@Override
	public String toString() {
		return "name=" + name + ", dob=" + dob + ", address=" + address + ", phoneNo="
				+ phoneNo + ", emailAddress=" + emailAddress;
	}
    
}
