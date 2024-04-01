package com.microservice.EmpPersonalDetails.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.EmpPersonalDetails.Exception.NoSuchEmployeeFoundException;
import com.microservice.EmpPersonalDetails.entity.PersonalDetail;
import com.microservice.EmpPersonalDetails.repository.PersonalDetailRepository;
import com.microservice.EmpPersonalDetails.request.PersonalDetailRequest;
import com.microservice.EmpPersonalDetails.response.PersonalDetailResponse;

@Service
public class PersonalDetailService {
	
	@Autowired
	PersonalDetailRepository personalDetailRepository;

    public PersonalDetailResponse addEmployeeDetails(PersonalDetailRequest personalDetail) {		
		
		PersonalDetail personalDetails= new PersonalDetail();
		personalDetails.setName(personalDetail.getName());
		personalDetails.setAddress(personalDetail.getAddress());
		personalDetails.setDob(personalDetail.getDob());
		personalDetails.setEmail(personalDetail.getEmail());
		personalDetails.setPhoneNo(personalDetail.getPhoneNo());
		personalDetails.setSalary(personalDetail.getSalary());
		personalDetailRepository.save(personalDetails);
		return new PersonalDetailResponse(personalDetails);
	}
	
	public PersonalDetailResponse getPersonDetail(Long empId) {		
		  try {
	            PersonalDetail personal = personalDetailRepository.findById(empId).orElseThrow(() ->
	                    new NoSuchElementException("PersonalDetail not found with empId " + empId));
	            
	            PersonalDetailResponse personalDetailResponse = new PersonalDetailResponse(personal);
	            return personalDetailResponse;
	        } catch (NoSuchElementException ex) {
	            throw new NoSuchEmployeeFoundException("PersonalDetail not found with empId " + empId);
	        }
	}
	
	public String deleteEmployeePersonaldetail(Long empId)  {
		 try {          
	            personalDetailRepository.deleteById(empId);
	            return "Personal details for employee with ID " + empId + " deleted successfully.";
	        } catch (NoSuchEmployeeFoundException ex) {           
	            throw new NoSuchEmployeeFoundException("No personal details found for employee with ID " + empId);
	        } catch (Exception ex) {
	            throw new RuntimeException("Unexpected error occurred while deleting personal details", ex);
	        }	
	}
	
	public void updatePersonDetail(PersonalDetailRequest personalDetail, Long empId) {
		 try {
			 if (!personalDetailRepository.existsById(empId)) {
	                throw new NoSuchEmployeeFoundException("PersonalDetail not found with empId " + empId);
	            }
			 
	            personalDetailRepository.findById(empId).ifPresent(detail -> {
	                detail.setName(personalDetail.getName());
	                detail.setAddress(personalDetail.getAddress());
	                detail.setDob(personalDetail.getDob());
	                detail.setEmail(personalDetail.getEmail());
	                detail.setPhoneNo(personalDetail.getPhoneNo());
	                detail.setSalary(personalDetail.getSalary());
	                
	                personalDetailRepository.save(detail);
	            });
	            
	           
	        } catch (NoSuchElementException ex) {
	            throw new NoSuchEmployeeFoundException("PersonalDetail not found with empId " + empId);
	        }catch(Exception ex){
	        	ex.getMessage();
	        }
	}
}
