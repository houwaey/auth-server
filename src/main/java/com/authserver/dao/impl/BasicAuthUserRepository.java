package com.authserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.authserver.abstracts.AbstractHibernateDao;
import com.authserver.dao.UserDao;
import com.authserver.model.BasicAuthUser;

@Repository
public class BasicAuthUserRepository extends AbstractHibernateDao<BasicAuthUser> implements UserDao<BasicAuthUser> {

	public BasicAuthUserRepository() {
		super();
		setClazz(BasicAuthUser.class);
	}
	
}