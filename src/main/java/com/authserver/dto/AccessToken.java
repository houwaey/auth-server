package com.authserver.dto;

public class AccessToken {

	private boolean tokenValid = false;
	private boolean tokenNonExpired = false;
	private Token tokenObject;

	public boolean isTokenValid() {
		return tokenValid;
	}

	public void setTokenValid(boolean tokenValid) {
		this.tokenValid = tokenValid;
	}

	public boolean isTokenNonExpired() {
		return tokenNonExpired;
	}

	public void setTokenNonExpired(boolean tokenNonExpired) {
		this.tokenNonExpired = tokenNonExpired;
	}

	public Token getTokenObject() {
		return tokenObject;
	}

	public void setTokenObject(Token tokenObject) {
		this.tokenObject = tokenObject;
	}
	
}
