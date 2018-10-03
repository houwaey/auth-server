package com.authserver.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authserver.abstracts.AbstractHibernateService;
import com.authserver.dao.OAuthDao;
import com.authserver.dto.AccessToken;
import com.authserver.dto.Token;
import com.authserver.interfaces.Operations;
import com.authserver.model.OAuthUser;
import com.authserver.service.OAuthService;
import com.authserver.util.Constant;

@Service
@Transactional
public class OAuthCoreService extends AbstractHibernateService<OAuthUser> implements OAuthService<OAuthUser> {

	@Autowired
	private OAuthDao<OAuthUser> dao;
	
	@Autowired
	private UUID generateUUID;
		
	public OAuthCoreService() {
		super();
	}

	@Override
	public OAuthUser generateAccessToken(OAuthUser entity) {
		long currentTime = System.currentTimeMillis() / 1000;
		long expirationTime = currentTime + Constant.TOKEN_EXPIRATION; // 5 minutes token expiration
		
		entity.setTokenExpiration(new Date(expirationTime * 1000));
		entity.setTimestamp(new Date(currentTime * 1000));
		entity.setAccessToken(generateUUID.toString());
		
		if (dao.merge(entity)) {
			return entity;
		}

		return null;
	}
	
	@Override
	public AccessToken initToken(String token) {
		AccessToken accessToken = new AccessToken();
		OAuthUser user = dao.findOneByKeyVal("accessToken", token);
		if (user == null) {
			accessToken.setTokenValid(false);
			return accessToken;
		}
		accessToken.setTokenValid(true);
		long currentTime = System.currentTimeMillis() / 1000;
		long expires_in = (user.getTokenExpiration().getTime() / 1000) - currentTime;
		if (expires_in <= 0) {
			accessToken.setTokenNonExpired(false);
			return accessToken;
		}
		accessToken.setTokenNonExpired(true);
		accessToken.setTokenObject(new Token(token, null, expires_in));
		return accessToken;
	}

	@Override
	protected Operations<OAuthUser> getDao() {
		return dao;
	}

}
