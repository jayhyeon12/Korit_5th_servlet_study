package com.study.product.serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

import com.study.product.dto.InsertUserReqDto;
import com.study.product.utils.RequestUtil;


@WebServlet("/user")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertUser() {
        super();
        
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InsertUserReqDto userReqDto = RequestUtil.convertJsonData(request, InsertUserReqDto.class);
		
		System.out.println(userReqDto);
		
	}

}
