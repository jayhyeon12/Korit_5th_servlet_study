package com.study.product.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.product.config.DBConnectionMgr;
import com.study.product.vo.ProductVo;

public class ProductDao {
	private static ProductDao instance;
	private DBConnectionMgr pool;
	
	private ProductDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		
		return instance;
	}
	
	public List<ProductVo> getProductList() {
		List<ProductVo> list = new ArrayList<>();
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			c = pool.getConnection();
			String sql = "select * from product_tb";
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVo vo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.productSize(rs.getString(4))
						.build();
				
				list.add(vo);
			}
			
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(c, pstmt, rs);
		}
		
		
		return list;
	}
	
	public ProductVo findProductByProductName(String productName) {
		ProductVo vo = null;
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			c = pool.getConnection();
			String sql = "select * from product_tb where product_name = ?";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.productSize(rs.getString(4))
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(c, pstmt, rs);
		}
		
		return vo;
	}
	
	public int save(ProductVo vo) {
		int successCount = 0;
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			c = pool.getConnection();
			String sql = "insert into product_tb values (0, ?, ?, ?)";
			pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, vo.getProductName());
			pstmt.setInt(2, vo.getProductPrice());
			pstmt.setString(3, vo.getProductSize());
			
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				vo.setProductId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(c, pstmt);
		} 
		
		return successCount;
	
	}
	
}