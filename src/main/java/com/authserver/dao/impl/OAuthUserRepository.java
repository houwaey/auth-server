package com.authserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.authserver.abstracts.AbstractHibernateDao;
import com.authserver.dao.UserDao;
import com.authserver.model.OAuthUser;

@Repository
public class OAuthUserRepository extends AbstractHibernateDao<OAuthUser> implements UserDao<OAuthUser> {

	public OAuthUserRepository() {
		super();
		setClazz(OAuthUser.class);
	}
	
}