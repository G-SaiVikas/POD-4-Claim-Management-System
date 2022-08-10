package com.claimservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claim")
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cId;
	private String claimDescription;
	private String claimStatus;
	private String adminClaimDescription;
	private String policyId;
	private String claimId;
	private String userId;
	private long claimAmount;
	
	public long getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(long claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Claim(long claimId, String claimDescription, String claimStatus, String adminClaimDescription, String userId,
			long pId) {
		super();
		this.cId= claimId;
		this.claimDescription = claimDescription;
		this.claimStatus = claimStatus;
		this.adminClaimDescription = adminClaimDescription;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(long claimId) {
		this.cId = claimId;
	}
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
