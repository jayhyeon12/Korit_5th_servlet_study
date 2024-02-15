package com.study.array.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.array.entity.DataObj;
import com.study.array.service.DataService;

@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataService dataService;
	
	public DataInsertServlet() {
		dataService = DataService.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		StringBuilder json = new StringBuilder();
		String requestData = null;
		
		/*
		 * " {
		 * 		"id": 1,
		 * 		"content": "aaaa"
		 * } "
		 */
		
		
		BufferedReader br = request.getReader();
		while((requestData = br.readLine()) != null) {
			json.append(requestData);
		}
	
		Gson gson = new Gson();
		DataObj dataobj = gson.fromJson(json.toString(), DataObj.class);
		
		System.out.println(dataobj);
		
		System.out.println(gson.toJson(dataobj));
		int responseBody = dataService.addData(dataobj);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", responseBody);
		responseMap.put("responsemessage", "데이터 추가 완료");
		
		response.setContentType("application/json");
		response.setStatus(201);
		response.getWriter().println(gson.toJson(responseMap));
		
	}
	
}