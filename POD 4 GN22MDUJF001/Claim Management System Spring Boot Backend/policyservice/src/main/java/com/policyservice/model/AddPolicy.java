package com.policyservice.model;

import java.time.LocalDate;

public class AddPolicy {
	private String policyOwnerName;
	private String policyType;
	private String vehicleRegNo;
	private String ownerAddress;
	private String phone;
	private String city;
	private String state;
	private String pincode;
	private String policyDuration;
	private LocalDate dateOfPolicyPurchase;
	private LocalDate dateOfPolicyExpire;
	
	public String getPolicyOwnerName() {
		return policyOwnerName;
	}
	public void setPolicyOwnerName(String policyOwnerName) {
		this.policyOwnerName = policyOwnerName;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPolicyDuration() {
		return policyDuration;
	}
	public void setPolicyDuration(String policyDuration) {
		this.policyDuration = policyDuration;
	}
	public LocalDate getDateOfPolicyPurchase() {
		return dateOfPolicyPurchase;
	}
	public void setDateOfPolicyPurchase(LocalDate dateOfPolicyBought) {
		this.dateOfPolicyPurchase = dateOfPolicyBought;
	}
	public LocalDate getDateOfPolicyExpire() {
		return dateOfPolicyExpire;
	}
	public void setDateOfPolicyExpire(LocalDate dateOfPolicyExpire) {
		this.dateOfPolicyExpire = dateOfPolicyExpire;
	}
	
	
	
}
