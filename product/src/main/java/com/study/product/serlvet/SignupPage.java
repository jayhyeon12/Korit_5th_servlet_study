package com.study.product.serlvet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/signup")
public class SignupPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public SignupPage() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext(); // 서버 내 설정 파일, 서버 전역 저장소
		application.setAttribute("key4", "value1");
		application.setAttribute("key2", "value2");
		application.setAttribute("key3", "value3");
		
		request.setAttribute("key1", "***");
		request.setAttribute("key5", "value5");
		request.setAttribute("key6", "value6");
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1000 * 60);
		session.setAttribute("key7", "value7");
		session.setAttribute("key8", "value8");
		
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
		
	}
	
}