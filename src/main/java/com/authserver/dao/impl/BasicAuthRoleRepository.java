package com.authserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.authserver.abstracts.AbstractHibernateDao;
import com.authserver.dao.UserDao;
import com.authserver.model.BasicAuthRole;

@Repository
public class BasicAuthRoleRepository extends AbstractHibernateDao<BasicAuthRole> implements UserDao<BasicAuthRole> {

	public BasicAuthRoleRepository() {
		super();
		setClazz(BasicAuthRole.class);
	}
	
}
