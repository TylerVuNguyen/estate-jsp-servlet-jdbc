package com.vujavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.vujavaweb.annotation.Column;
import com.vujavaweb.annotation.Table;
import com.vujavaweb.db.DBConnection;
import com.vujavaweb.mapper.ResultSetMapper;
import com.vujavaweb.paging.PageRequest;
import com.vujavaweb.paging.Pageble;
import com.vujavaweb.paging.Sorter;
import com.vujavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {
	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		// đoạn này để getClass T
		// getClass() chính là AbstractJDBC
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	/*
	 * @Override public List<T> query(String sql, Object... parameters) {
	 * ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>(); try (Connection
	 * conn = DBConnection.getConnection(); PreparedStatement statement =
	 * conn.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
	 * if (conn != null) { for (int i = 0; i < parameters.length; i++) { int index =
	 * i + 1; statement.setObject(index, parameters[i]); } return
	 * resultSetMapper.mapRow(resultSet, this.zClass); } } catch (SQLException ex) {
	 * System.out.println(ex.getMessage()); } return null; }
	 * 
	 * @Override public void update(String sql, Object... parameters) { Connection
	 * conn = null; PreparedStatement statement = null; try { conn =
	 * DBConnection.getConnection(); conn.setAutoCommit(false); statement =
	 * conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); if (conn !=
	 * null) { for (int i = 0; i < parameters.length; i++) { int index = i + 1;
	 * statement.setObject(index, parameters[i]); } statement.executeUpdate();
	 * conn.commit(); } } catch (SQLException ex) { if (conn != null) { try {
	 * conn.rollback(); } catch (SQLException e) {
	 * System.out.println(e.getMessage()); } } } finally { if (conn != null) { try {
	 * conn.close(); } catch (SQLException ex) { ex.printStackTrace(); } } }
	 * 
	 * }
	 * 
	 * @Override public Long insert(String sql, Object... parameters) { Connection
	 * conn = null; PreparedStatement statement = null; ResultSet resultSet = null;
	 * try { conn = DBConnection.getConnection(); conn.setAutoCommit(false);
	 * statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); if
	 * (conn != null) { for (int i = 0; i < parameters.length; i++) { int index = i
	 * + 1; statement.setObject(index, parameters[i]); } int rowsInserted =
	 * statement.executeUpdate(); resultSet = statement.getGeneratedKeys();
	 * conn.commit(); if (rowsInserted > 0) { while (resultSet.next()) { Long id =
	 * resultSet.getLong(1); return id; } }
	 * 
	 * } } catch (SQLException ex) { if (conn != null) { try { conn.rollback(); }
	 * catch (SQLException e) { System.out.println(e.getMessage()); } } } finally {
	 * if (conn != null) { try { conn.close(); } catch (SQLException ex) {
	 * ex.printStackTrace(); } } } return null;
	 * 
	 * }
	 */
	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLIsert();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}
				// check thằng cha
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						statement.setObject(indexParent, field.get(object));
						indexParent += 1;
					}
					parentClass = parentClass.getSuperclass();
				}
				int rowsInserted = statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (resultSet.next()) {
						Long id = resultSet.getLong(1);
						return id;
					}
				}

			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	private String createSQLIsert() {
		String table = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table tableName = zClass.getAnnotation(Table.class);
			table = tableName.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}

		}
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}

			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = "INSERT INTO " + table + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		return sql;
	}

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLUpdate();
			statement = conn.prepareStatement(sql);
			if (conn != null) {
				// moi dau chi moi tra ve object(com.vujavaweb.entity.BuildingEntity@77665508)
				// tay phai getClass(); để lấy được class(com.vujavaweb.entity.BuildingEntity)
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}
				// check thằng cha
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				Object id = null;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if (!name.equalsIgnoreCase("id")) {
							statement.setObject(indexParent, field.get(object));
							indexParent += 1;
						} else {
							id = field.get(object);
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String createSQLUpdate() {
		String table = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table tableName = zClass.getAnnotation(Table.class);
			table = tableName.name();
		}
		StringBuilder sets = new StringBuilder("");
		String where = null;

		for (Field field : zClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = columnName + " = ?";
				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(",");
					}
					sets.append(value);
				}
			}

		}
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = columnName + " = ?";
					if (!columnName.equals("id")) {
						if (sets.length() > 1) {
							sets.append(",");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = "UPDATE " + table + " SET " + sets.toString() + where;
		return sql;
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			if (conn != null) {
				String sql = createSQLDelete(id);
				statement = conn.prepareStatement(sql);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String createSQLDelete(Long id) {
		String table = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table tableName = zClass.getAnnotation(Table.class);
			table = tableName.name();
		}

		String sql = "DELETE FROM " + table + " WHERE id=" + id;
		return sql;
	}

	@Override
	public T findbyid(Long id) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		String sql = createSQLFindById(id);
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			List<T> result = resultSetMapper.mapRow(resultSet, this.zClass);
			return result.get(0);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	private String createSQLFindById(Long id) {
		String table = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table tableName = zClass.getAnnotation(Table.class);
			table = tableName.name();
		}

		String sql = "SELECT * FROM " + table + " WHERE id=" + id;
		return sql;
	}

	@Override
	public List<T> findAll(Map<String, Object> Properties,PageRequest  pageble,Object... where) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		StringBuilder sql = createSQLFindALl(Properties);
		if (where != null && where.length > 0) {
			sql.append( where[0]);
		}
		if(pageble != null) {
			if(pageble.getSorter() != null) {
				Sorter sorter = pageble.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy()+"");
			}	
			if(pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			}	
		}	
		try {
			conn = DBConnection.getConnection();
			statement = conn.prepareStatement(sql.toString());
			resultSet = statement.executeQuery();
			if (conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private StringBuilder createSQLFindALl(Map<String, Object> properties, Object... where) {
		String table = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table tableName = zClass.getAnnotation(Table.class);
			table = tableName.name();
		}

		StringBuilder result = new StringBuilder("SELECT * FROM " + table + " WHERE 1=1");
		if (properties != null && properties.size() > 0) {
			String[] params = new String[properties.size()];
			Object[] value = new Object[properties.size()];
			int i = 0;
			for (Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				value[i] = item.getValue();
				i++;
			}
			for (int j = 0; j < params.length; j++) {
				if (value[j] instanceof String) {
					result.append(" and LOWER(" + params[j] + ") LIKE '%" + value[j] + "%'");
				} else if (value[j] instanceof Integer) {
					result.append(" and " + params[j] + " = " + value[j] + " ");
				}
			}
		}

		return result;
	}

}
