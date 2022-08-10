package com.userservice.data;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.userservice.model.AddPolicy;

@FeignClient(name="policy-ms")
public interface PolicyServiceClient {
	
	@GetMapping("users/allPolicies/{userId}")
	public List<Policy> getPolicies(@PathVariable String userId);
	
	@PostMapping("users/addPolicy/{userId}")
	public void addPolicy(@PathVariable("userId") String userId, AddPolicy addPolicy);
	
	@PutMapping("users/updatePolicy/{policyId}")
	public void updatePolicyStatus(@PathVariable("policyId") String policyId);
}
