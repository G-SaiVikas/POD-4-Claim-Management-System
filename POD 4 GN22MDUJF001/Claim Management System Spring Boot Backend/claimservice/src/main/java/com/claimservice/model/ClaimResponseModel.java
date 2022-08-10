package com.claimservice.model;

public class ClaimResponseModel {

		private String claimId;
		private String policyId;
		private String claimDescription;
		private long claimAmount;
		
		
		public long getClaimAmount() {
			return claimAmount;
		}
		public void setClaimAmount(long claimAmount) {
			this.claimAmount = claimAmount;
		}
		public String getClaimId() {
			return claimId;
		}
		public void setClaimId(String claimId) {
			this.claimId = claimId;
		}
		public String getPolicyId() {
			return policyId;
		}
		public void setPolicyId(String policyId) {
			this.policyId = policyId;
		}
		public String getClaimDescription() {
			return claimDescription;
		}
		public void setClaimDescription(String claimDescription) {
			this.claimDescription = claimDescription;
		}
		
}
