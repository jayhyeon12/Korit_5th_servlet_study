package com.study.product.serlvet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.UserDto;


@WebServlet("/signin.do")
public class SignInPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SignInPage() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto dbuser = UserDto.builder()
				.username("test")
				.password("5743")
				.name("***")
				.email("gfdstera412")
				.build();
		
		request.getServletContext().setAttribute("dbuser", dbuser);
		
		request.getRequestDispatcher("/WEB-INF/views/signin.html").forward(request, response);
		
	}

}
