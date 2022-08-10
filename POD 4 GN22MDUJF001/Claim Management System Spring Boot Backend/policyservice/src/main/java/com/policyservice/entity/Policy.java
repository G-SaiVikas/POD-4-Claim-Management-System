package com.policyservice.entity;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long pId;
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
	private String policyId;
	private boolean status;
	private String userId;
	
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	
	public Policy(LocalDate dateOfPolicyExpire) {
		super();
		this.dateOfPolicyExpire = dateOfPolicyExpire;
	}

	public LocalDate getDateOfPolicyExpire() {
		return dateOfPolicyExpire;
	}

	public void setDateOfPolicyExpire(LocalDate dateOfPolicyExpire) {
		this.dateOfPolicyExpire = dateOfPolicyExpire;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

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

	public Policy() {
		super();
		
	}
	public Policy(int pId, String policyOwnerName, String policyType, String vehicleRegNo, String ownerAddress,
			String phone, String city, String state, String pincode, String policyDuration, LocalDate dateOfPolicyBought) {
		super();
		this.pId = pId;
		this.policyOwnerName = policyOwnerName;
		this.policyType = policyType;
		this.vehicleRegNo = vehicleRegNo;
		this.ownerAddress = ownerAddress;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.policyDuration = policyDuration;
		this.dateOfPolicyPurchase= dateOfPolicyBought;
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

