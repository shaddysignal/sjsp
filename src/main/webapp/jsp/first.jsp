<%@page import="com.improveit.simpleapp.model.User"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String phonenumber = "";
	String email = "";
	String password = "";
	String repeatpassword = "";
	if((Map<String,String>)request.getAttribute("errors") != null) {
		phonenumber = ((Map<String,String>)request.getAttribute("errors")).get("phonenumber");
		email = ((Map<String,String>)request.getAttribute("errors")).get("email");
		password = ((Map<String,String>)request.getAttribute("errors")).get("password");
		repeatpassword = ((Map<String,String>)request.getAttribute("errors")).get("repeatpassword");
	}
%>
<!DOCTYPE html5>
<html>
<head>
	<title>First step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="first" action="next_step.kitty" method="POST" commandName="user" autocomplete="true">
		<form:input path="firstName" id="user_firstName" placeholder="First Name"/>
		<form:input path="secondName" id="user_secondName" placeholder="Second Name"/>
		<form:input path="fathersName" id="user_fathersName" placeholder="Fathers Name"/>
		<form:input path="phonenumber" id="user_phonenumber" placeholder="Phonenumber"/>
			<label><%= phonenumber != null ? phonenumber : "" %></label>
		<form:input path="email" id="user_email" placeholder="Email"/>
			<label><%= email != null ? email : "" %></label>
		<form:password path="password" id="user_password" placeholder="Password"/>
			<label><%= password != null ? password : "" %></label>
		<input name="rpassword" type="password" id="user_rpassword" placeholder="Repeat Password"/>
			<label><%= repeatpassword != null ? repeatpassword : "" %></label>
		<div>
			<a href="/">cancel</a>
			<a href="javascript:{}" onclick="document.forms['first'].submit()">next step</a>
		</div>
	</form:form>
</body>
</html>
