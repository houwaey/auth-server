package com.authserver.dao;

import java.io.Serializable;

import com.authserver.interfaces.Operations;

public interface OAuthDao<T extends Serializable> extends Operations<T> {
	
}
