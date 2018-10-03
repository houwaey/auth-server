package com.authserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.authserver.abstracts.AbstractHibernateDao;
import com.authserver.dao.OAuthDao;
import com.authserver.model.OAuthUser;

@Repository
public class OAuthRepository extends AbstractHibernateDao<OAuthUser> implements OAuthDao<OAuthUser> {

	public OAuthRepository() {
		super();
		setClazz(OAuthUser.class);
	}
	
}