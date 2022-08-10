package com.userservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.userservice.model.AddClaim;
import com.userservice.model.AddPolicy;
import com.userservice.shared.UserDto;

public interface UserService extends UserDetailsService {
	ResponseEntity<?> createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
	void addPolicy(String userId,AddPolicy addPolicy);
	void updateJwtToken(String userId,String jwtToken);
	void addClaim(String policyId,String userId,AddClaim claim);
	ResponseEntity<?> getClaimStatus(String policyId,String userId);
	ResponseEntity<?> getAllClaims(String userId);
	void updatePolicyStatus(String policyId);
}
