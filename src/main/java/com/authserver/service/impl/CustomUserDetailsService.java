package com.authserver.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authserver.abstracts.AbstractHibernateService;
import com.authserver.dao.UserDao;
import com.authserver.interfaces.Operations;
import com.authserver.model.BasicAuthRole;
import com.authserver.model.BasicAuthUser;
import com.authserver.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService extends AbstractHibernateService<BasicAuthUser> 
	implements UserService<BasicAuthUser>, UserDetailsService {

	@Autowired
	private UserDao<BasicAuthUser> dao;
	
	public CustomUserDetailsService() {
		super();
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BasicAuthUser user = dao.findOneByKeyVal("username", username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found exception");
		}
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), getGrantedAuthorities(user.getAuthorities()));
	}
	
	public List<GrantedAuthority> getGrantedAuthorities(Set<BasicAuthRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Iterator<BasicAuthRole> iRoles = roles.iterator();
		while (iRoles.hasNext()) {
			BasicAuthRole role = iRoles.next();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		}
		return authorities;
	}

	@Override
	protected Operations<BasicAuthUser> getDao() {
		return dao;
	}

}
