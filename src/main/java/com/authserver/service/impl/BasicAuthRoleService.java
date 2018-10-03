package com.authserver.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authserver.abstracts.AbstractHibernateService;
import com.authserver.dao.UserDao;
import com.authserver.interfaces.Operations;
import com.authserver.model.BasicAuthRole;
import com.authserver.service.UserService;

@Service
@Transactional
public class BasicAuthRoleService extends AbstractHibernateService<BasicAuthRole> implements UserService<BasicAuthRole> {

	@Autowired
	private UserDao<BasicAuthRole> dao;
	
	public BasicAuthRoleService() {
		super();
	}
	
	@Override
	protected Operations<BasicAuthRole> getDao() {
		return dao;
	}

}