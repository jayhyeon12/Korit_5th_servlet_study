<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% // 스크립트릿: 자바 문법 작성 가능
	List<String> names = List.of("이00", "박00", "정00");
// ssr에서는 cors가 없음(같은 서버)
%>
    
<%
// S(erver)S(ide)R(endering) - 서버사이드렌더링
// model1:  
// model2: 
// mvc(model, view, controller): 
// - model: 데이터  -> DTO
// - view: html(화면)  -> html, jsp
// - controller: model과 view 제어, 요청/응답 처리 -> servlet
	
	String name = "000";
	String inputvalue = "test";
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<ul>
		<li><%=name%></li>
	</ul>
	<div>
		<input value="<%=inputvalue%>">
	</div>
</body>
</html>