<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
	<title>First step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="first" action="next_step.kitty" method="POST" commandName="user">
		<form:input path="firstName" id="user_firstName" placeholder="First Name" /> 
		<form:input path="secondName" id="user_secondName"	placeholder="Second Name" /> 
		<form:input path="fathersName" id="user_fathersName" placeholder="Fathers Name" />
		<form:input path="phonenumber" id="user_phonenumber" placeholder="Phonenumber" />
		<form:input path="email" id="user_email" placeholder="Email" /> 
		<form:password path="password" id="user_password" placeholder="Password" />
		<input type="password" id="user_rpassword" placeholder="Repeat Password" />
		<a href="/">cancel</a>
		<a href="javascript:{}" onclick="document.forms['first'].submit()">next step</a>
	</form:form>
	<script type="text/javascript" src="/res/js/validate.js"></script>
</body>
</html>
