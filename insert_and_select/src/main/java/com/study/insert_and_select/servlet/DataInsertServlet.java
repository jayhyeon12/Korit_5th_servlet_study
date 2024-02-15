package com.study.insert_and_select.servlet;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.servlet.ServletException;
import java.servlet.annotation.WebServlet;
import java.servlet.http.HttpServlet;
import java.servlet.http.HttpServletRequest;
import java.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.NonNullElementWrapperList;
import com.study.insert_and_select.dao.StudentDao;
import com.study.insert_and_select.entity.Student;

@WebServlet("/DataInsertServlet2")
public class DataInsertServlet<HttpServletRequest, HttpServletResponse> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DataInsertServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		
		String readData = null;
		
		BufferedReader r = request.getReader();
		
		/* 
		 * "{
		 * 		"name": "김도진", 
		 * 		"age": "29"
		 * }"
		 */
		
		while((readData = r.readLine()) != null) {
			sb.append(readData);
			
		}
		
		Gson gson = new Gson();
		
		// json → map
		Map<String, Object> map = gson.fromJson(sb.toString(), Map.class);
		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
		
		
		// json → 객체
		Student s = gson.fromJson(sb.toString(), Student.class);
		System.out.println(s);
		System.out.println(s.getName());
		System.out.println(s.getAge());
		
		StudentDao studentDao = StudentDao.getInstance();
		
		Student findStudent = studentDao.findStudentByName(s.getName());
		
		if(findStudent != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errormessage", "이미 등록된 이름입니다");
			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		int successCount = studentDao.saveStudent(s);
		
		
		Map<String, Object> responsemap = new HashMap<>();
		responsemap.put("status", 201);
		responsemap.put("data", "응답 성공");
		responsemap.put("successCount", successCount);
		
		response.setStatus(201);
		response.setContentType("application/json");
		
		PrintWriter writer =  response.getWriter();
		writer.println(gson.toJson(responsemap));
	}

}
