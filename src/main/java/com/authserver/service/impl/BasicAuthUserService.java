package com.authserver.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authserver.abstracts.AbstractHibernateService;
import com.authserver.dao.UserDao;
import com.authserver.interfaces.Operations;
import com.authserver.model.BasicAuthUser;
import com.authserver.service.UserService;

@Service
@Transactional
public class BasicAuthUserService extends AbstractHibernateService<BasicAuthUser> implements UserService<BasicAuthUser> {

	@Autowired
	private UserDao<BasicAuthUser> dao;

	public BasicAuthUserService() {
		super();
	}
	
	@Override
	protected Operations<BasicAuthUser> getDao() {
		return dao;
	}

}
