<%@page import="com.improveit.simpleapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Boolean afterFinish = (Boolean)request.getAttribute("afterFinish"); 
	if(afterFinish != null && afterFinish == true)
		response.sendRedirect("/finale.kitty");
	User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html5>
<html>
<head>
	<title>Cabinet</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<h2>Hello <%= user.getFirstName() %>!</h2>
	<div>
		<p>You can logout your self by clicking on</p>
		<a href="/undefine.kitty" id="undefine">here</a>
	</div>
</body>
</html>
