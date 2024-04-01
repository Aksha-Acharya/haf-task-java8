package com.microservice.EmpPersonalDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.EmpPersonalDetails.entity.PersonalDetail;

@Repository
public interface PersonalDetailRepository extends JpaRepository<PersonalDetail, Long>  {

//	@Query("SELECT pd.personalId, pd.name, pd.dob, pd.address, pd.phoneNo, pd.email FROM PersonalDetail pd WHERE pd.id = :id")
//    PersonalDetailDTO findPersonalDetailsById(@Param("id") Long personalId);
}
