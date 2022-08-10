package com.claimservice.model;

public class ClaimNotFound {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ClaimNotFound(String message) {
		super();
		this.message = message;
	}
	
}
