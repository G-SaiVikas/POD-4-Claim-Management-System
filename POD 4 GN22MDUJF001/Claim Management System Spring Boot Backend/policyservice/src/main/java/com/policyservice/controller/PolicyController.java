package com.policyservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.policyservice.entity.Policy;
import com.policyservice.model.AddPolicy;
import com.policyservice.service.PolicyService;

@RestController
@RequestMapping("/users")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	/*
	 * This method fetches all policy for a particular user*/
	@GetMapping("/allPolicies/{userId}")
	public List<Policy> getPolicies(@PathVariable("userId") String userId) throws Exception{
		List<Policy> allPoliciesList=new ArrayList<>();
		try {
			 allPoliciesList =this.policyService.getAllPolicy(userId);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return allPoliciesList;
	}
	/*
	 * This method adds a policy for a particular user*/
	@PostMapping("/addPolicy/{userId}")
	public void addPolicy(@PathVariable("userId") String userId, @RequestBody AddPolicy policy) throws Exception {
		 this.policyService.addNewPolicy(userId,policy);
		
	}
	/*
	 * This method updates policy status according to claim*/
	@PutMapping("/updatePolicy/{policyId}")
	public void updatePolicy(@PathVariable("policyId") String policyId) {
		this.policyService.updatePolicyStatus(policyId);
	}
}
