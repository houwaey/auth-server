package com.authserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "oauth_user")
public class OAuthUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "access_token")
	private String accessToken;
	
	@Column(name = "token_type")
	private String tokenType;
	
	@Column(name = "token_expiration", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpiration;
	
	@Column(name = "timestamp", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Date getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Date tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "OAuthUser [id=" + id + ", username=" + username + ", password=" + password + ", accessToken="
				+ accessToken + ", tokenType=" + tokenType + ", tokenExpiration=" + tokenExpiration + ", timestamp="
				+ timestamp + "]";
	}

}
