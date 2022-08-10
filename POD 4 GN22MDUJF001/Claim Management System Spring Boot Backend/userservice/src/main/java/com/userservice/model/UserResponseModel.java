package com.userservice.model;

import java.util.List;

import com.userservice.data.Policy;

public class UserResponseModel {

	private List<Policy> allPolicies;
	
	public List<Policy> getAllPolicies() {
		return allPolicies;
	}
	public void setAllPolicies(List<Policy> allPolicies) {
		this.allPolicies = allPolicies;
	}
	
	
	
}
