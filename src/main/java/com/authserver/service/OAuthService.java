package com.authserver.service;

import java.io.Serializable;

import com.authserver.dto.AccessToken;
import com.authserver.interfaces.Operations;

public interface OAuthService<T extends Serializable> extends Operations<T> {
	
	public T generateAccessToken(T entity);
	
	public AccessToken initToken(String token);
	
}
