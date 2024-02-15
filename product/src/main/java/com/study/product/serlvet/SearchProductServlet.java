package com.study.product.serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.service.ProductService;
import com.study.product.utils.ResponseEntity;


@WebServlet("/products")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    
    public SearchProductServlet() {
        super();
        productService = ProductService.getInstance();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity.ofJson(response, 200, productService.searchProducts());
	}

}