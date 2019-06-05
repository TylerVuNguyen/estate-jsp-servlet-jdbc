package com.vujavaweb.repository;

import java.util.List;
import java.util.Map;

import com.vujavaweb.paging.PageRequest;
import com.vujavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	/*List<T> query(String sql,Object... parameters);
	void update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);*/
	List<T> findAll(Map<String, Object> properties,PageRequest pageble,Object... where);
	Long insert(Object object);
	void update(Object object);
	void delete(Long id);
	T findbyid(Long id);
}
