package com.vujavaweb.dto;

import java.sql.Timestamp;

public class BaseDTO {

	private Long id;
	private String createdby;
	private String modifiedby;
	private Timestamp createddate;
	private Timestamp modifieddate;
	private int maxPageItem = 2;
	private int page = 1;
	
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

	public int getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(int maxPageItem) {
		this.maxPageItem = maxPageItem;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
