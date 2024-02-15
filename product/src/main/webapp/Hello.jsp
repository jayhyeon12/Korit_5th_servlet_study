<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% // 스크립트릿: 자바 문법 작성 가능
	List<String> names = List.of("이00", "박00", "정00");
// ssr에서는 cors가 없음(같은 서버)
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
		<%
			for(String name : names) {
				
		%>
				<li>${name}</li>
		<%
			}
		%>
	</ul>
</body>
</html>