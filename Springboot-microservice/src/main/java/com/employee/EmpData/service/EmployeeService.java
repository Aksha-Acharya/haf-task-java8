package com.employee.EmpData.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.EmpData.dto.EmpAllDataRequest;
import com.employee.EmpData.encryption.LoginServiceImpl;
import com.employee.EmpData.entity.Officedata;
import com.employee.EmpData.entity.Personaldata;
import com.employee.EmpData.entity.UserAccess;
import com.employee.EmpData.entity.repository.OfficedataRepository;
import com.employee.EmpData.entity.repository.PersonaldataRepository;
import com.employee.EmpData.entity.repository.UserAccessRepository;
import com.employee.EmpData.entity.repository.dao.EmpAllDataResponse;
import com.employee.EmpData.entity.repository.dao.OfficedataDao;
import com.employee.EmpData.entity.repository.dao.PersonaldataDao;
import com.employee.EmpData.entity.repository.dao.PersonaldatawithoutSalary;
import com.employee.EmpData.exception.EmployeeNotFoundException;
import com.employee.EmpData.utility.GenerateUsername;

@Service
public class EmployeeService {
	
	@Autowired
	OfficedataRepository officedataRepository;
	@Autowired
	PersonaldataRepository personaldataRepository;
	@Autowired
	UserAccessRepository userAccessRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	GenerateUsername generateUsername;
	
	@Autowired
	LoginServiceImpl loginService;

	public String createEmployee(EmpAllDataRequest employee) {
		
		Officedata officedata = new Officedata();
		officedata.setEmployeeName(employee.getEmployeeName());
		officedata.setProjectName(employee.getProjectName());
		officedata.setDateOfJoining(employee.getDateOfJoining());
		officedata.setDesignation(employee.getDesignation());
		officedata.setEmployeeType(employee.getEmployeeType());
		officedataRepository.save(officedata);
		Personaldata personaldata = new Personaldata();
		personaldata.setName(employee.getEmployeeName());
		personaldata.setDob(employee.getDob());
		personaldata.setEmailAddress(employee.getEmailAddress());
		personaldata.setPhoneNo(employee.getPhoneNo());
		personaldata.setSalary(employee.getSalary());
		personaldata.setAddress(employee.getAddress());
		personaldata.setOfficedata(officedata);
		personaldataRepository.save(personaldata);
		
		UserAccess userAccess = new UserAccess();
		userAccess.setUsername(employee.getUsername());
		userAccess.setPassword(passwordEncoder.encode(employee.getPassword()));
		userAccess.setRoles(employee.getRoles());
		userAccess.setOfficedata(officedata);
		userAccessRepository.save(userAccess);
		
		return "Employee created successfully with empId :"+ officedata.getEmployeeId()+"\n"
		+"and username:"+employee.getUsername() + "  password :"+employee.getPassword();
	
	}

	public EmpAllDataResponse getEmployee(Long employeeId) throws EmployeeNotFoundException{	
		try {
		Officedata officedata = officedataRepository.findById(employeeId).get();
		Personaldata personaldata =personaldataRepository.findById(employeeId).get();		
		OfficedataDao officedataDao=new OfficedataDao(officedata);
		PersonaldatawithoutSalary personaldatawithoutSalary =new PersonaldatawithoutSalary(personaldata);
		EmpAllDataResponse emp = new EmpAllDataResponse(officedataDao, personaldatawithoutSalary);
		return emp;
		}catch(NoSuchElementException ex) {
			throw new EmployeeNotFoundException("Employee not found with empId "+ employeeId);
		}
		
	}

	public PersonaldataDao getPersonalDetails(Long employeeId) throws EmployeeNotFoundException {
		try {
		Personaldata personaldata =personaldataRepository.findById(employeeId).get();		
		PersonaldataDao personaldataDao = new PersonaldataDao(personaldata);
		return personaldataDao;
	}catch(NoSuchElementException ex) {
		throw new EmployeeNotFoundException("Personal data not found for empId "+ employeeId);
		}
	}

	public String updateEmployee(Long employeeId, EmpAllDataRequest employee) throws EmployeeNotFoundException {
		try {
			if(officedataRepository.existsById(employeeId) || personaldataRepository.existsById(employeeId)) {
				Personaldata personaldata = personaldataRepository.findById(employeeId).get();
				personaldata.setAddress(employee.getAddress());
				personaldata.setDob(employee.getDob());
				personaldata.setEmailAddress(employee.getEmailAddress());
				personaldata.setName(employee.getEmployeeName());
				personaldata.setPhoneNo(employee.getPhoneNo());
				personaldata.setSalary(employee.getSalary());
				personaldataRepository.save(personaldata);		
				Officedata officedata = officedataRepository.findById(employeeId).get();
				officedata.setEmployeeName(employee.getEmployeeName());
				officedata.setDesignation(employee.getDesignation());
				officedata.setDateOfJoining(employee.getDateOfJoining());
				officedata.setEmployeeType(employee.getEmployeeType());
				officedata.setProjectName(employee.getProjectName());
				officedata.setPersonalDetails(personaldata);
				officedataRepository.save(officedata);
				
				return "Employee details updated successfully with empId :"+ employeeId;
			}else {
				throw new EmployeeNotFoundException("Employee not found with empId "+ employeeId +" to update");
			}
		}catch(NoSuchElementException exc){
			throw new EmployeeNotFoundException("Employee not found with empId "+ employeeId +" to update");
		 }
	}

	public String deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
	try {
		
		if(officedataRepository.existsById(employeeId)) {
			officedataRepository.deleteById(employeeId);
			return "Employee details deleted successfully";
		}else {
			throw new EmployeeNotFoundException("Employee not found with empId "+ employeeId +" to perform delete");
		}	
	}catch(NoSuchElementException exc) {
       throw new EmployeeNotFoundException("Employee not found with empId "+ employeeId +" to perform delete");
	}
	
	}

}
