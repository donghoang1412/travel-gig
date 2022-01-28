package com.synergisticit.jwt.models;

import java.util.Set;

import com.synergisticit.domain.Role;

public class AuthenticationRequest {
	private String userName;
	private String password;
	private Set<Role> roles;
	
	
	
	public AuthenticationRequest(String userName, String password, Set<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public AuthenticationRequest() {
		super();
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
