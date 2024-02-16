<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<%

// 내장 객체

/* 저장소
application 객체 저장소
- 생명 주기: 서버가 켜진 후 꺼지기 전까지

request 객체 저장소
- 생명 주기: 요청이 들어온 뒤 응답 전까지

session 객체 저장소
- 생명 주기: 서버가 켜진 뒤 꺼지기 전이나 세션 만료 전까지

key 값이 같을 때 접근 우선 순위: request - session - application
*/

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" placeholder="username">
	<input type="password" placeholder="password">
	<input type="text" placeholder="name">
	<input type="email" placeholder="email">
	
	<img src="/product/hellotest/java.png">
	
	<button onclick="handleSignupSubmit();">회원가입</button>
	<script src="/product/static/js/signup.js"></script>
</body>
</html>