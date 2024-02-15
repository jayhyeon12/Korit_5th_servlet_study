package com.study.servlet_study.reporsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookRepository {
	private static BookRepository instance;
	private DBConnectionMgr pool;
	
	private BookRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static BookRepository getInstance() {
		if(instance == null) {
			instance = new BookRepository();
		}
			return instance;
	}
	
	public int saveBook(Book book) {
		Connection c = null;
		PreparedStatement ps = null;
		
			try {
				c = pool.getConnection();
				String sql = "insert into author_tb\r\n"
						+ "values (0, ?)";
				ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getAuthor().getAuthorName());
				ps.executeUpdate(); 				// insert, update, delete 시 사용되는 메소드
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					book.getAuthor().setAuthorId(rs.getInt(1));
					
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
				ps.setString(1, book.getPublisher().getPublisherName());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					book.getPublisher().setPublisherId(rs.getInt(1));
					
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
				ps.setString(1, book.getBookName());
				ps.setInt(2, book.getAuthor().getAuthorId());
				ps.setInt(3, book.getPublisher().getPublisherId());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					book.setBookId(rs.getInt(1));
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			
			} finally {
				pool.freeConnection(c, ps);
			}
			
			return 1;
		}
	
		public Book findBookByBookId(int bookId) {
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Book fb = null;
			
			try {
				c = pool.getConnection();
				String sql = "select\r\n"
						+ "	*\r\n"
						+ "from\r\n"
						+ "	book_view\r\n"
						+ "where\r\n"
						+ "	book_id = ?";
				ps = c.prepareStatement(sql);
				ps.setInt(1, bookId);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					fb = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder()
								.authorId(rs.getInt(3))
								.authorName(rs.getString(4))
								.build())
						.publisher(Publisher.builder()
							.publisherId(rs.getInt(5))
							.publisherName(rs.getString(6))
							.build())
						.build();
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			} finally {
				pool.freeConnection(c, ps, rs);
			}
			
			return fb;
			
			}
		
		public List<Book> searchBookList(Map<String, String> params) {
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Book> bl = new ArrayList<>();
			
			Map<String, String> keyData = new HashMap<>();
			keyData.put("bookName", "book_name");
			keyData.put("authorName", "author_name");
			keyData.put("publisherName", "publisher_name");
			
			try {
				c = pool.getConnection();
				String sql = "select\r\n"
						+ "	*\r\n"
						+ "from\r\n"
						+ "	book_view\r\n"
						+ "where\r\n"
						+ "	? = 1";
				for(String key : params.keySet()) {
					sql += " or " + keyData.get(key) + " like ?";
				}
				ps = c.prepareStatement(sql);
				ps.setInt(1, params.isEmpty() ? 1 : 0);
				
				int i = 2;
				for(String key : params.keySet()) {
					ps.setString(i, "%" + params.get(key) + "%");
					i++;
				}
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Book b = Book.builder()
							.bookId(rs.getInt(1))
							.bookName(rs.getString(2))
							.author(Author.builder()
									.authorId(rs.getInt(3))
									.authorName(rs.getString(4))
									.build())
							.publisher(Publisher.builder()
								.publisherId(rs.getInt(5))
								.publisherName(rs.getString(6))
								.build())
							.build();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bl;
		}				
	}