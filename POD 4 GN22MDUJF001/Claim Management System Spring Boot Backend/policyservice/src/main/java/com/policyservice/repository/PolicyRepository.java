package com.policyservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.policyservice.entity.Policy;


public interface PolicyRepository extends CrudRepository<Policy, Long> {

	List<Policy> findByUserId(String userId);
	
	@Transactional
	@Modifying
	@Query("update Policy p set p.status = :policyStatus Where p.policyId = :policyId")
	void updatePolicy(@Param("policyId") String policyId,@Param("policyStatus") boolean policyStatus);
}
