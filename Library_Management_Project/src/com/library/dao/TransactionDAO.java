package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
	private DBDAO dbDao;

	public TransactionDAO(DBDAO dbDao) {
		this.dbDao = dbDao;
	}

	public boolean issueBook(int studentId, int bookId) throws SQLException {
		String sql = "INSERT INTO transactions (student_id, book_id, issue_date) VALUES (?, ?, NOW())";

		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbDao.getDbCon();
			pst = con.prepareStatement(sql);

			pst.setInt(1, studentId);
			pst.setInt(2, bookId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} finally {
			// Close PreparedStatement
			if (pst != null) {
				pst.close();
			}
			// Close Connection
			if (con != null) {
				con.close();
			}
		}
	}

	public boolean returnBook(int studentId, int bookId) throws SQLException {
		String sql = "UPDATE transactions SET return_date = NOW() WHERE student_id = ? AND book_id = ?";

		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbDao.getDbCon();
			pst = con.prepareStatement(sql);

			pst.setInt(1, studentId);
			pst.setInt(2, bookId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} finally {
			// Close PreparedStatement
			if (pst != null) {
				pst.close();
			}
			// Close Connection
			if (con != null) {
				con.close();
			}
		}
	}
}
