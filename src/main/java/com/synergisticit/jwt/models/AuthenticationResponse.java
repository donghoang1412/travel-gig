package com.synergisticit.jwt.models;

import java.util.Set;

public class AuthenticationResponse {
	
	private final String jwt;
	private final String userName;
	private final Set<String> roles;
	
	
	public AuthenticationResponse(String jwt, String userName, Set<String>  roles) {
		super();
		this.jwt = jwt;
		this.userName = userName;
		this.roles = roles;
	}

	public String getJwt() {
		return jwt;
	}

	public String getUserName() {
		return userName;
	}

	public Set<String>  getRoles() {
		return roles;
	}

}
