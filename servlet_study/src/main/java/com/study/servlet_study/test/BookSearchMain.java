package com.study.servlet_study.test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookSearchMain {
	
	public static void main(String[] args) {
		/*
		 * 검색 도서명 입력 >> 바
		 * 도서명 / 저자 / 출판사
		 */
		
		Scanner sc = new Scanner(System.in);
		String searchValue = null;
		
		System.out.println(" 검색할 도서명 입력 >> ");
		searchValue = sc.nextLine();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Book> bl = new ArrayList<>();
		
		try {
			c = pool.getConnection();
			String sql = "select\r\n"              
					+ "	bt.book_id,\r\n"            // 1
					+ "    bt.book_name,\r\n"       // 2
					+ "    bt.author_id,\r\n"		// 3
					+ "    at.author_name,\r\n"		// 4
					+ "    bt.publisher_id,\r\n"	// 5
					+ "    pt.publisher_name\r\n"	// 6
					+ "from\r\n"
					+ "	book_tb bt\r\n"
					+ "    left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "where\r\n"
					+ "	bt.book_name like ?";
			
			ps = c.prepareStatement(sql);
			ps.setString(1, "%" + searchValue + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Author a = Author.builder()
						.authorId(rs.getInt(3))
						.authorName(rs.getString(4))
						.build();
				
				Publisher p = Publisher.builder()
						.publisherId(rs.getInt(5))
						.publisherName(rs.getString(6))
						.build();
				
				Book b = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(a)
						.publisher(p)
						.build();
				
				bl.add(b);
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pool.freeConnection(c, ps, rs);;
		}
		
		System.out.println("도서명 / 출판사 / 저자");
		
		for (Book book : bl) {
			System.out.println(book.getBookName() + " / " + book.getPublisher().getPublisherName() + " / " + book.getAuthor().getAuthorName());
		}
			
	}

}