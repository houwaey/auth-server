package com.authserver.service;

import java.io.Serializable;

import com.authserver.interfaces.Operations;

public interface UserService<T extends Serializable> extends Operations<T> {

}
