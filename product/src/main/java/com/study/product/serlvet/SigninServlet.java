package com.study.product.serlvet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.product.dto.UserDto;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;


@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SigninServlet() {
        super();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDto ud = RequestUtil.convertJsonData(request, UserDto.class);
		
		UserDto dbuser = (UserDto) request.getServletContext().getAttribute("dbuser");
		
		boolean isMatchUsername = Objects.equals(dbuser.getUsername(), ud.getUsername());
		boolean isMatchPassword = Objects.equals(dbuser.getUsername(), ud.getPassword());
		
		if(!(isMatchPassword && isMatchUsername)) {
			Map<String, String> map = new HashMap<>();
			
			map.put("errormessage", "사용자 정보 재확인");
			ResponseEntity.ofJson(response, 403, map);
			
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("principalUser", "dbUser");
	
		ResponseEntity.ofJson(response, 200, dbuser);
	}

}
