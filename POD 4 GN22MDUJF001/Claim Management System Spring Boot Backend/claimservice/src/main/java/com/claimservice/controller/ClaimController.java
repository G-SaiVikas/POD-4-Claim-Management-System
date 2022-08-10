package com.claimservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claimservice.model.AddClaim;
import com.claimservice.services.ClaimService;


@RestController
@RequestMapping("/users")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	/*This method adds a claim for a particular user with a specific policy
	 * */
	@PostMapping("/addClaim/{userId}/{policyId}")
	public void addClaim(@PathVariable("policyId") String policyId,@PathVariable("userId") String userId,@RequestBody AddClaim claim) {
		this.claimService.addClaim(policyId,userId ,claim);
	}
	/*This method returns the current claim status of a particular policy
	 * */
	@GetMapping("/claimStatus/{userId}/{policyId}")
	public ResponseEntity<?> getClaimStatus(@PathVariable("policyId") String policyId,@PathVariable("userId") String userId) {
		return this.claimService.getClaimStatus(policyId,userId);
	}
	/*This method returns all the claims made by the user in their account
	 * */
	@GetMapping("/getAllClaims/{userId}")
	public ResponseEntity<?> getAllClaims(@PathVariable("userId") String userId){
		return this.claimService.getAllClaims(userId);
	}
}
