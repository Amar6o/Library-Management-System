package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDAO {

	private static Connection dbCon;
	private static String dbURL;
	private static String dbDriver;
	private static String userName;
	private static String passWord;

	// Initialize database connection values
	private static void dbInit() {
		try {
			dbDriver = "com.mysql.cj.jdbc.Driver";
			dbURL = "jdbc:mysql://localhost:3306/librarydb";
			userName = "root";
			passWord = "amar@2627";

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Database connection
	public static void connect() throws ClassNotFoundException, SQLException {
		dbInit();
		Connection con = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL, userName, passWord);
			setDbCon(con);

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw cnfe; // Rethrow the exception to handle it outside
		} catch (SQLException sql) {
			sql.printStackTrace();
			throw sql; // Rethrow the exception to handle it outside
		}
	}

	// Get Connection
	public static Connection getDbCon() {
		return dbCon;
	}

	// Set Connection
	public static void setDbCon(Connection con) {
		dbCon = con;
	}

	// Close connection
	public static void close() throws SQLException {
		if (dbCon != null) {
			dbCon.close();
		}
	}
}
