package com.userservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.userservice.data.ClaimServiceClient;
import com.userservice.model.AdminModel;
import com.userservice.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminContoller {

	@Autowired
	private ClaimServiceClient claimServiceClient;
	
	@Autowired 
	private UserService userService;
	
	/*
	 * This method logs out the Admin
	 * */
	@GetMapping("/logout/{adminId}")
	public void logout(@PathVariable("adminId") String adminId) {
		String userId=adminId;
		this.userService.updateJwtToken(userId,"");
	}
	/*This method returns all pending claims for the Admin
	 * */
	@GetMapping("/pendingClaims")
	public ResponseEntity<?> pendingClaims(){
		return this.claimServiceClient.pendingClaims();
	}
	/*This method returns all accepted claims for the Admin
	 * */
	@GetMapping("/acceptedClaims")
	public ResponseEntity<?> acceptedClaims(){
		return this.claimServiceClient.acceptedClaims();
	}
	/*This method returns all rejected claims for the Admin
	 * */
	@GetMapping("/rejectedClaims")
	public ResponseEntity<?> rejectedClaims(){
		return this.claimServiceClient.rejectedClaims();
	}
	/*This method accepts a claim made by the user
	 * */
	@PutMapping("/acceptClaim/{claimId}")
	public ResponseEntity<?> acceptClaim(@PathVariable("claimId") String claimId,@RequestBody AdminModel adminModel){
		return this.claimServiceClient.acceptClaim(claimId,adminModel);
	}
	/*This method rejects a claim made by the user
	 * */
	@PutMapping("/rejectClaim/{claimId}")
	public ResponseEntity<?> rejectClaim(@PathVariable("claimId") String claimId,@RequestBody AdminModel adminModel){
		return this.claimServiceClient.rejectClaim(claimId,adminModel);
	}
}
