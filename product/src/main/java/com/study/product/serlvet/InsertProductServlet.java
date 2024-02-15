package com.study.product.serlvet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertProductReqDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;


@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService;   
    
    public InsertProductServlet() {
        super(); 
        productService = ProductService.getInstance();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InsertProductReqDto reqDto = RequestUtil.convertJsonData(request, InsertProductReqDto.class);
		
		if(productService.isDuplicatedProductName(reqDto.getProductName())) {
			Map<String, Object> map = new HashMap<>();
			map.put("errormessage", "이미 등록된 상품명");
			
			ResponseEntity.ofJson(response, 400, map);
			return;
			
		}
		
		
		ResponseEntity.ofJson(response, 201, productService.addProduct(reqDto));
		
	}

}
