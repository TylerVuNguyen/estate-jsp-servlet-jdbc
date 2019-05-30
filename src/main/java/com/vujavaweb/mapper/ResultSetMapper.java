package com.vujavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.vujavaweb.annotation.Column;
import com.vujavaweb.annotation.Entity;

public class ResultSetMapper<T> {
	public List<T> mapRow(ResultSet rs, Class<T> zClass) {
		List<T> results = new ArrayList<>();
		try {
			if (zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				// lay duoc tat ca cac field trong class
				Field[] fields = zClass.getDeclaredFields();
				while(rs.next()) {
					T object = (T) zClass.newInstance();
					//get gias tri cua mot row trong resultset va set vao trong entity
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i+1);
						//current class
						ConvertResultToEntity(fields, columnName, columnValue, object);
						// parent class
						Class<?> parentClass = zClass.getSuperclass();
						while (parentClass != null) {
							Field[] fieldParents = parentClass.getDeclaredFields();
							//logic convert data
							ConvertResultToEntity(fieldParents,columnName,columnValue,object);
							parentClass = parentClass.getSuperclass();
						}
					}
					results.add(object);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

		return results;
	}

	private void ConvertResultToEntity(Field[] fieldParents, String columnName, Object columnValue, T object) throws IllegalAccessException, InvocationTargetException {
		for (Field field : fieldParents) {
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if(column.name().equals(columnName) && columnValue != null) {
					BeanUtils.setProperty(object, field.getName(), columnValue);
					break;
				}
			}
		}	
	}
}
