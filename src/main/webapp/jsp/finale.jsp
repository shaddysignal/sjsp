<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
	<title>Cabinet</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<h2>Hello <%= session.getCreationTime() %>!</h2>
	<div>
		<p>You can logout your self by clicking on</p>
		<a href="/undefine.kitty" onclick="return confirm('Are you sure?')">here</a>
	</div>
	<div>
		<p>Or you can update data by clicking on</p>
		<a href="/first.kitty">here</a>
	</div>
</body>
</html>
