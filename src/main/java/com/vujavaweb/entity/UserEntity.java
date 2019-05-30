package com.vujavaweb.entity;

import com.vujavaweb.annotation.Column;
import com.vujavaweb.annotation.Entity;
import com.vujavaweb.annotation.Table;

@Entity
@Table
public class UserEntity extends BaseEntity {
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "status")
	private int status;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
