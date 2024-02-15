package com.study.servlet_study.test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {
	
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = pool.getConnection();
			String sql = "select\r\n"
					+ "	*\r\n"
					+ "from\r\n"
					+ "	author_tb\r\n"
					+ "where\r\n"
					+ "	author_name = ?";
			ps = c.prepareStatement(sql);
			
			rs = ps.executeQuery(); // 쿼리 실행, select 일 때만 작성(insert, update, delete 는 출력X)
			
			List<Author> al = new ArrayList<>();
			
			while(rs.next()) {
				al.add(Author.builder()
				.authorId(rs.getInt(1))
				.authorName(rs.getString(2))
				.build());
			}
			
			al.forEach(author -> System.out.println(author));
			
			for(Author author : al) {
				System.out.println(author);
			}
			
			for(int i = 0; i < al.size(); i++) {
				Author author = al.get(i);
				System.out.println(author);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pool.freeConnection(c, ps, rs);
		
		}
		
	}
	
}