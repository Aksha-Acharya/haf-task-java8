package com.microservice.EmpOfficeDetails.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.EmpOfficeDetails.Exception.NoSuchEmployeeFoundException;
import com.microservice.EmpOfficeDetails.entity.OfficeDetail;
import com.microservice.EmpOfficeDetails.feignClients.PersonalDetailFeignClient;
import com.microservice.EmpOfficeDetails.repository.OfficeDetailRepository;
import com.microservice.EmpOfficeDetails.request.EmpOfficeDetailRequest;
import com.microservice.EmpOfficeDetails.request.EmployeeAllDataRequest;
import com.microservice.EmpOfficeDetails.request.PersonalDetailRequest;
import com.microservice.EmpOfficeDetails.response.EmpOfficeDetailResponse;
import com.microservice.EmpOfficeDetails.response.EmployeeAlldataResponse;
import com.microservice.EmpOfficeDetails.response.PersonalDetailResponse;


@Service
public class OfficeDetailService {

	@Autowired
	OfficeDetailRepository officeDetailRepository;
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	PersonalDetailFeignClient personalClient;

	public EmpOfficeDetailResponse addEmployeeDetails(EmployeeAllDataRequest empdetail) throws AccessDeniedException{

		OfficeDetail officeDetail = new OfficeDetail();
		officeDetail.setEmployeeName(empdetail.getEmployeeName());
		officeDetail.setProjectName(empdetail.getProjectName());
		officeDetail.setDateOfJoining(empdetail.getDateOfJoining());
		officeDetail.setDesignation(empdetail.getDesignation());
		officeDetail.setEmployeeType(empdetail.getEmployeeType());
	//	officeDetail.setPersonalId(empdetail.getPersonalId());
		officeDetailRepository.save(officeDetail);
		
		EmpOfficeDetailResponse emp=new EmpOfficeDetailResponse(officeDetail);
		emp.setPersonalDetailResponse(personalClient.getPersonalDetail(officeDetail.getEmployeeId()));
		return emp;
	}

	public void addPersonalDetail(PersonalDetailRequest personalDetailRequest) {
		webClient.post().uri("/employees/personal").bodyValue(personalDetailRequest).retrieve().bodyToMono(Void.class)
				.subscribe(); 
	}

	public EmpOfficeDetailResponse getAllDetail(Long empId) throws NoSuchEmployeeFoundException, AccessDeniedException {
		 try {
		        Optional<OfficeDetail> officeDetailOptional = officeDetailRepository.findById(empId);
		        if (officeDetailOptional.isPresent()) {
		            OfficeDetail officeDetail = officeDetailOptional.get();
		            EmpOfficeDetailResponse empOfficeDetailResponse = new EmpOfficeDetailResponse(officeDetail);
		            empOfficeDetailResponse.setPersonalDetailResponse(personalClient.getPersonalDetail(officeDetail.getEmployeeId()));
		            return empOfficeDetailResponse;
		        } else {
		            throw new NoSuchEmployeeFoundException("Employee not found with empID " + empId);
		        }
		    } catch (NoSuchElementException ex) {
		        throw new NoSuchEmployeeFoundException("Employee not found with empID " + empId);
		    }
	}
	
//	public PersonalDetailResponse getPersonalDetail(Long personalId)throws NoSuchEmployeeFoundException  {
//		 try {
//		        Mono<PersonalDetailResponse> detail = webClient.get().uri("/employees/personal/" + personalId).retrieve()
//		                .bodyToMono(PersonalDetailResponse.class);
//		        return detail.block();
//		    } catch (NoSuchElementException ex) {
//		        throw new NoSuchEmployeeFoundException("Employee not found with empID " + personalId);
//		    }
//	}

	public PersonalDetailResponse getPersonalDetailResponse(Long personalId) throws NoSuchEmployeeFoundException,AccessDeniedException {
		
		return personalClient.getPersonalDetail(personalId);
//		Mono<PersonalDetailResponsewithSalary> detail = webClient.get().uri("/employees/personal/" + personalId)
//				.retrieve().bodyToMono(PersonalDetailResponsewithSalary.class);
//		return detail.block();
	}

	public String updateEmployeeDetails(EmployeeAllDataRequest newOfficeDetail, Long empId) throws AccessDeniedException{
		Optional<OfficeDetail> officeDetailOldValue = Optional.ofNullable(officeDetailRepository.findById(empId).get());
		if (officeDetailOldValue.isPresent()) {
			OfficeDetail oldOfficedetailwithNewValue = officeDetailOldValue.get();
			oldOfficedetailwithNewValue.setEmployeeName(newOfficeDetail.getEmployeeName());
			oldOfficedetailwithNewValue.setDesignation(newOfficeDetail.getDesignation());
			oldOfficedetailwithNewValue.setDateOfJoining(newOfficeDetail.getDateOfJoining());
			oldOfficedetailwithNewValue.setEmployeeType(newOfficeDetail.getEmployeeType());
			oldOfficedetailwithNewValue.setProjectName(newOfficeDetail.getProjectName());
		//	oldOfficedetailwithNewValue.setPersonalId(newOfficeDetail.getPersonalId());
			officeDetailRepository.save(oldOfficedetailwithNewValue);
		}
		else {
			throw new  NoSuchEmployeeFoundException("Employee not found with empID " + empId);
		}
		PersonalDetailRequest personalDetailRequest = new PersonalDetailRequest(newOfficeDetail);
		updatePersonalDetail(personalDetailRequest, empId);
		//personalClient.updatePersonaDetail(null, empId, null)
		return "employee detail updated successfully";
	}

	public void updatePersonalDetail(PersonalDetailRequest personalDetailRequest, Long personalId) {
		webClient.put().uri("/employees/personal/{id}", personalId).bodyValue(personalDetailRequest).retrieve()
				.bodyToMono(Void.class).subscribe();
		
	}

	public void deleteEmployeeDetails(Long empId) throws AccessDeniedException {
		Optional<OfficeDetail> officeDetailOptional = officeDetailRepository.findById(empId);
		if (officeDetailOptional.isPresent()) {
			OfficeDetail officeDetail = officeDetailOptional.get();
			//Long personalId = officeDetail.getPersonalId();
			officeDetailRepository.deleteById(officeDetail.getEmployeeId());
			personalClient.deletePersonalDetail(officeDetail.getEmployeeId());
		//webClient.delete().uri("/employees/personal/" + personalId).retrieve().bodyToMono(Void.class).block();
		}
		else {
			throw new  NoSuchEmployeeFoundException("Employee not found with empID " + empId);
		}
	}
}