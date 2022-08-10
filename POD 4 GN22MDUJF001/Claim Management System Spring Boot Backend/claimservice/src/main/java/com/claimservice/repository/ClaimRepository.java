package com.claimservice.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.claimservice.entity.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Long> {
	
	public Claim findByPolicyId(String policyId);
	
	public List<Claim> findByUserId(String userId);
	
	public List<Claim> findByClaimStatus(@Param("claimStatus") String claimStatus);
	
	@Transactional
	@Modifying
	@Query("update Claim c set c.claimStatus = :status Where c.claimId = :claimId")
	public void updateClaimStatus(@Param("claimId") String claimId,@Param("status") String status);
	
	@Transactional
	@Modifying
	@Query("update Claim c set c.adminClaimDescription = :adminClaimDescription Where c.claimId = :claimId")
	public void updateAdminClaimDescription(@Param("claimId") String claimId,@Param("adminClaimDescription") String adminClaimDescription);
}
