package com.policyservice.service;

import java.util.List;

import com.policyservice.entity.Policy;
import com.policyservice.model.AddPolicy;

public interface PolicyService {

	List<Policy> getAllPolicy(String userId) throws Exception;
	
	void addNewPolicy(String userId,AddPolicy policy);
	
	void updatePolicyStatus(String policyId);
}
