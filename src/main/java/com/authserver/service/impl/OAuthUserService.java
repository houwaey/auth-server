package com.authserver.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authserver.abstracts.AbstractHibernateService;
import com.authserver.dao.UserDao;
import com.authserver.interfaces.Operations;
import com.authserver.model.OAuthUser;
import com.authserver.service.UserService;

@Service
@Transactional
public class OAuthUserService extends AbstractHibernateService<OAuthUser> implements UserService<OAuthUser> {

	@Autowired
	private UserDao<OAuthUser> dao;
	
	public OAuthUserService() {
		super();
	}
	
	@Override
	protected Operations<OAuthUser> getDao() {
		return dao;
	}

}
