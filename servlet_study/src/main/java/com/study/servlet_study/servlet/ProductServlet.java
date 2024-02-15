package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Product;
import com.study.servlet_study.service.ProductService;


@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    public ProductServlet() {
        super();
        productService = ProductService.getInstance();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		int price = 0;
		
		try {
			price = Integer.parseInt(request.getParameter("price"));
		} catch(NumberFormatException e) {
			response.setStatus(400);
			response.getWriter().println("숫자만 입력 가능");
			return;
		}
		
		Product p = Product.builder()
				.productName(request.getParameter("productName"))
				.price(price)
				.size(request.getParameter("size"))
				.color(request.getParameter("color"))
				.build();
		
		if (productService.getProduct(p.getProductName()) != null) {
			response.setStatus(400);
			response.getWriter().println("이미 등록된 상품");
			return;
			
		}
		productService.addProduct(p);
		response.setStatus(201);
		response.getWriter().println("상품 등록 완료");
		
	}

}