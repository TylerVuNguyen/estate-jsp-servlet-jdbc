package com.vujavaweb.entity;

import java.sql.Timestamp;

import com.vujavaweb.annotation.Column;

public class BaseEntity {
	@Column(name="id")
	private Long id;
	@Column(name="createdby")
	private String createdby;
	@Column(name="modifiedby")
	private String modifiedby;
	@Column(name="createddate")
	private Timestamp createddate;
	@Column(name="modifieddate")
	private Timestamp modifieddate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	
}
