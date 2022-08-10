package com.userservice.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.userservice.model.AdminModel;

@FeignClient(name="admin-ms")
public interface AdminServiceClient {
	
	@GetMapping("admin/pendingClaims")
	public ResponseEntity<?> pendingClaims();
	
	@GetMapping("admin/acceptedClaims")
	public ResponseEntity<?> acceptedClaims();
	
	@GetMapping("admin/rejectedClaims")
	public ResponseEntity<?> rejectedClaims();
	
	@PutMapping("admin/acceptClaim/{claimId}")
	public ResponseEntity<?> acceptClaim(@PathVariable("claimId") String claimId,AdminModel adminModel);
	
	@PutMapping("admin/rejectClaim/{claimId}")
	public ResponseEntity<?> rejectClaim(@PathVariable("claimId") String claimId,AdminModel adminModel);
}
