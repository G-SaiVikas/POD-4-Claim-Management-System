package com.claimservice.model;

public class ClaimStatusModel {

	private String claimDescription;
	private String claimStatus;
	private long claimAmount;
	private String adminClaimDescription;

	
	public String getClaimDescription() {
		return claimDescription;
	}
	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}
	public long getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(long claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getAdminClaimDescription() {
		return adminClaimDescription;
	}
	public void setAdminClaimDescription(String adminClaimDescription) {
		this.adminClaimDescription = adminClaimDescription;
	}
		
}
