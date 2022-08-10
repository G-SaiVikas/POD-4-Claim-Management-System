package com.claimservice.services;

import org.springframework.http.ResponseEntity;

import com.claimservice.model.AddClaim;
import com.claimservice.model.AdminModel;

public interface ClaimService {
	
	void addClaim(String policyId,String userId,AddClaim claim);
	
	ResponseEntity<?> getClaimStatus(String policyId,String userId);
	
	ResponseEntity<?> getAllClaims(String userId);
	
	ResponseEntity<?> pendingClaims();
	
	ResponseEntity<?> acceptedClaims();
	
	ResponseEntity<?> rejectedClaims();
	
	ResponseEntity<?> acceptClaim(String claimId,AdminModel adminModel);
	
	ResponseEntity<?> rejectClaim(String claimId,AdminModel adminModel);
	
}
