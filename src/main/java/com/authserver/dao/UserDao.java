package com.authserver.dao;

import java.io.Serializable;

import com.authserver.interfaces.Operations;

public interface UserDao<T extends Serializable> extends Operations<T> {

}
