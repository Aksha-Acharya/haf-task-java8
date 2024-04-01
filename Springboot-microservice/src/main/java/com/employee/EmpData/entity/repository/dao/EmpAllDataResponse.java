package com.employee.EmpData.entity.repository.dao;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class EmpAllDataResponse {
	
	OfficedataDao officedata;
	PersonaldatawithoutSalary personaldata;

	public EmpAllDataResponse(OfficedataDao officedataDao, PersonaldatawithoutSalary personaldatawithoutSalary) {
		this.officedata = officedataDao;
		this.personaldata = personaldatawithoutSalary;
	}
	
}
