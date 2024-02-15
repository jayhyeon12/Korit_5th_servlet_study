package com.study.insert_and_select.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.insert_and_select.config.DBConfig;
import com.study.insert_and_select.entity.Student;

public class StudentDao {

private static StudentDao instance;
	
	private StudentDao() {}
	
	public static StudentDao getInstance() {
		if(instance == null) {
			instance = new StudentDao();
			
		}
		return instance;
	}
	
	public Student findStudentByName(String name){
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		// DB 커넥터 드라이브 클래스 이름			
			
			c = DriverManager.getConnection(DBConfig.URL, DBConfig.PASSWORD, DBConfig.USERNAME);
			String sql = "select * from student_tb where student_name = ?";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				s = Student.builder()
						.StudentId(rs.getInt(1))
						.name(rs.getString(2))
						.age(rs.getInt(3))
						.build();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(rs != null) {
					pstmt.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(c != null) {
					c.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return s;
	}
	
	
	public int saveStudent(Student s) {
		Connection c = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		// DB 커넥터 드라이브 클래스 이름
			
			c = DriverManager.getConnection(DBConfig.URL, DBConfig.PASSWORD, DBConfig.USERNAME);
			String sql = "insert into student_tb(student_name, student_age) values (?, ?)";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, s.getName());
			pstmt.setInt(2, s.getAge());
			successCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(c != null) {
					c.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return successCount;
	
	}
		
	public List<Student> getStudentListAll() {
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		// DB 커넥터 드라이브 클래스 이름			
			
			c = DriverManager.getConnection(DBConfig.URL, DBConfig.PASSWORD, DBConfig.USERNAME);
			String sql = "select * from student_tb";
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student s = Student.builder()
						.StudentId(rs.getInt(1))
						.name(rs.getString(2))
						.age(rs.getInt(3))
						.build();
				
				students.add(s);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(rs != null) {
					pstmt.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(c != null) {
					c.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();	
			
			}
			
		}
		
		return students;
	}
	
}
