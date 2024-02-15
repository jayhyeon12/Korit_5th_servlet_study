package com.study.servlet_study.reporsitory;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.entity.Product;

public class ProductRepository {
	private static ProductRepository instance;
	private List<Product> productList;
	
	private ProductRepository() {
		productList = new ArrayList<>();
		
	}
	
	public static ProductRepository getInstance() {
		if (instance == null) {
			instance = new ProductRepository();
		}
		return instance;
	}
	
	public int saveProduct(Product product) {
		productList.add(product);
		return 1;
	}
	
	public Product findProductByProductname(String productname) {
		Product p = null;
		
		for(Product product : productList) {
			if (product.getProductName().equals(productname)) {
				p = product;
				break;
			}
		}
		
		return p;
		
	}
	
	
	
	
}