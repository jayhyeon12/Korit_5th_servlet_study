package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookInsertMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		System.out.println(" 도서명 >> ");
		bookName = sc.nextLine();
		System.out.println(" 저자 >> ");
		authorName = sc.nextLine();
		System.out.println(" 출판사 >> ");
		publisherName = sc.nextLine();
		
		Book b = Book.builder()
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection c = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			c = pool.getConnection();
			String sql = "insert into author_tb\r\n"
					+ "values (0, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, b.getAuthor().getAuthorName());
			ps.executeUpdate(); 				// insert, update, delete 시 사용되는 메소드
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				b.getAuthor().setAuthorId(rs.getInt(1));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			pool.freeConnection(c, ps);
		}
	
	
		try {
			c = pool.getConnection();
			String sql = "insert into publisher_tb\r\n"
					+ "values (0, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, b.getPublisher().getPublisherName());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				b.getPublisher().setPublisherId(rs.getInt(1));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			pool.freeConnection(c, ps);
		}
	
		try {
			c = pool.getConnection();
			String sql = "insert into book_tb\r\n"
					+ "values (0, ?, ?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, b.getBookName());
			ps.setInt(2, b.getAuthor().getAuthorId());
			ps.setInt(3, b.getPublisher().getPublisherId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				b.setBookId(rs.getInt(1));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			pool.freeConnection(c, ps);
		}
		
		System.out.println(" 추가된 도서 정보 ");
		System.out.println(b);
		
	}
	
}		