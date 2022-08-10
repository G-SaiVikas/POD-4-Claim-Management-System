package com.claimservice.shared;

public class ClaimDto {
	
	private String claimDescription;
	private String claimStatus;
	private String adminClaimDescription;
	private String policyId;
	private String claimId;
	
	public String getClaimDescription() {
		return claimDescription;
	}
	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
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
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
}
