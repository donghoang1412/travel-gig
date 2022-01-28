package com.synergisticit.domain;

public class UserResponse {
	private final String userName;
	private final String customerMobile;
	public UserResponse(String userName, String customerMobile) {
		super();
		this.userName = userName;
		this.customerMobile = customerMobile;
	}
	public String getUserName() {
		return userName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	
	
}
