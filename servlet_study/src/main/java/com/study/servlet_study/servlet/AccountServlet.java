package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.service.AccountService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountService accountService;
   
    public AccountServlet() {
        super();
        accountService = AccountService.getInstance();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		Account a = accountService.getAccount(username);
		response.setStatus(200);
		response.getWriter().println(a);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		Account a = Account.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
		
		int body = accountService.addAccount(a);		
		response.setStatus(201);
		response.getWriter().println(body);
			
	}

}