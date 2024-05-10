package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.bean.Student;

public class StudentDAO {
	private DBDAO dbDao;

	public StudentDAO(DBDAO dbDao) {
		this.dbDao = dbDao;
	}

	public boolean addStudent(Student student) throws SQLException {
		String sql = "insert into students (student_name, email, phone) values(?,?,?)";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, student.getStudentName());
			pst.setString(2, student.getEmail());
			pst.setString(3, student.getPhone());

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = new ArrayList<>();
		String sql = "select * from students";

		try (Connection con = dbDao.getDbCon();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentName(rs.getString("student_name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				students.add(student);

			}
		}
		return students;
	}

	public boolean updateStudent(Student student) throws SQLException {
		String sql = "update students set student_name=?, email=?, phone=? where student_id=? ";

		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, student.getStudentName());
			pst.setString(2, student.getEmail());
			pst.setString(3, student.getPhone());
			pst.setInt(4, student.getStudentId());

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public boolean deleteStudent(int studentId) throws SQLException {
		String sql = "delete from students where student_id=?";

		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, studentId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public Student getStudentById(int studentId) throws SQLException {
		String sql = "SELECT * FROM students WHERE student_id = ?";
		Student student = null;

		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, studentId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					student = new Student();
					student.setStudentId(rs.getInt("student_id"));
					student.setStudentName(rs.getString("student_name"));
					student.setEmail(rs.getString("email"));
					student.setPhone(rs.getString("phone"));
				}
			}
		}

		return student;
	}

}
