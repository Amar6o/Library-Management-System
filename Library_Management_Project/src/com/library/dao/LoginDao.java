package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.bean.LoginBean;

public class LoginDao {
	private String url;
	private String dbUsername;
	private String dbPassword;

	public LoginDao(String url, String dbUsername, String dbPassword) {

		this.url = url;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public LoginBean getUserByUsername(String username) {
		LoginBean user = null;
		String sql = "select * from librarians where name=?";

		try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				user = new LoginBean();
				user.setUsername(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean validateUser(String username, String password) {
		LoginBean user = getUserByUsername(username);

		return user != null && user.getPassword().equals(password);
	}

}
