package com.claimservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claimservice.model.AdminModel;
import com.claimservice.services.ClaimService;

@RestController
@RequestMapping("/admin")
public class AdminClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	/*This method returns all pending claims for the Admin
	 * */
	@GetMapping("/pendingClaims")
	public ResponseEntity<?> pendingClaims(){
		return	this.claimService.pendingClaims();
	}
	/*This method returns all rejected claims for the Admin
	 * */
	@GetMapping("/rejectedClaims")
	public ResponseEntity<?> rejectedClaims(){
		return this.claimService.rejectedClaims();
	}
	/*This method returns all accepted claims for the Admin
	 * */
	@GetMapping("/acceptedClaims")
	public ResponseEntity<?> acceptedClaims(){
		return this.claimService.acceptedClaims();
	}
	/*This method accepts a claim made by the user
	 * */
	@PutMapping("/acceptClaim/{claimId}")
	public ResponseEntity<?> acceptClaim(@PathVariable("claimId") String claimId,@RequestBody AdminModel adminModel){
		return this.claimService.acceptClaim(claimId,adminModel);
	}
	/*This method rejects a claim made by the user
	 * */
	@PutMapping("/rejectClaim/{claimId}")
	public ResponseEntity<?> rejectClaim(@PathVariable("claimId")  String claimId,@RequestBody AdminModel adminModel){
		return this.claimService.rejectClaim(claimId,adminModel);
	}
}
