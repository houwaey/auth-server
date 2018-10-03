package com.authserver.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "authrole")
public class BasicAuthRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "rolename")
	private String roleName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	@Override
	public String toString() {
		return "BasicAuthRole [id=" + id + ", roleName=" + roleName + "]";
	}
	
}
