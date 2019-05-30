package com.vujavaweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection()  {
		Connection conn = null;
		String url= "jdbc:mysql://localhost:3306/estate4-2019?useUnicode=true&amp;characterEncoding=utf8";
		String username = "root";
		String password = "2202";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			conn = DriverManager.getConnection(url, username, password);
			if(conn!=null) {
				System.out.println("Connect Success");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
