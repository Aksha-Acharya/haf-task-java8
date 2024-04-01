package com.microservice.EmpOfficeDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.EmpOfficeDetails.entity.OfficeDetail;

@Repository
public interface OfficeDetailRepository extends JpaRepository<OfficeDetail, Long> {

}
