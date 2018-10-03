package com.authserver.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "authuser")
public class BasicAuthUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "authuser_authrole",
			joinColumns = { @JoinColumn(name = "authuser_id") },
			inverseJoinColumns = { @JoinColumn(name = "authrole_id") })
	private Set<BasicAuthRole> authorities;
	
	@Column(name = "accountnonexpired")
	private boolean accountNonExpired;
	
	@Column(name = "accountnonlocked")
	private boolean accountNonLocked;
	
	@Column(name = "credentialsnonexpired")
	private boolean credentialsNonExpired;
	
	@Column(name = "enabled")
	private boolean enabled;

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

	public Set<BasicAuthRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<BasicAuthRole> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "BasicAuthUser [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
				+ (authorities != null ? toString(authorities, maxLen) : null) + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + ", enabled=" + enabled + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	
	
}
