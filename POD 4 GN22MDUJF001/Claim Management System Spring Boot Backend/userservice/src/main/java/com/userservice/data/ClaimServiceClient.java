package com.userservice.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.userservice.model.AddClaim;
import com.userservice.model.AdminModel;

@FeignClient(name="claim-ms")
public interface ClaimServiceClient {

	@PostMapping("users/addClaim/{userId}/{policyId}")
	public void addClaim(@PathVariable("policyId") String policyId,@PathVariable("userId") String userId ,AddClaim claim);
	@GetMapping("users//claimStatus/{userId}/{policyId}")
	public ResponseEntity<?> getClaimStatus(@PathVariable("policyId") String policyId,@PathVariable("userId") String userId);
	@GetMapping("users/getAllClaims/{userId}")
	public ResponseEntity<?> getAllClaims(@PathVariable("userId") String userId);
	
	@GetMapping("admin/pendingClaims")
	public ResponseEntity<?> pendingClaims();
	@GetMapping("admin/rejectedClaims")
	public ResponseEntity<?> rejectedClaims();
	@GetMapping("admin/acceptedClaims")
	public ResponseEntity<?> acceptedClaims();
	@PutMapping("admin/acceptClaim/{claimId}")
	public ResponseEntity<?> acceptClaim(@PathVariable("claimId") String claimId,AdminModel adminModel);
	@PutMapping("admin/rejectClaim/{claimId}")
	public ResponseEntity<?> rejectClaim(@PathVariable("claimId") String claimId,AdminModel adminModel);
	
}
