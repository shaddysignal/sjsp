<%@page import="java.util.Map"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String birthdate = "";
	String serial = "";
	String number = "";
	String serialnumber = "";
	Map<String,String> errors = (Map<String,String>)request.getAttribute("errors");
	if(errors != null) {
		birthdate = errors.get("phonenumber");
		serial = errors.get("email");
		number = errors.get("password");
		serialnumber = errors.get("repeatpassword");
	}
%>
<!DOCTYPE html5>
<html>
<head>
	<title>Second step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="second" action="/next_step.kitty" method="POST" commandName="user" autocomplete="true">	
		<form:hidden path="id" />
		<form:input path="birthdate" id="user_birthdate" placeholder="Bithdate" />
			<label><%= birthdate != null ? birthdate : "" %></label>
		<form:input path="serial" id="user_serial" placeholder="Serial" />
			<label><%= serial != null ? serial : "" %></label>
			<label><%= serialnumber != null ? serialnumber : "" %></label>
		<form:input path="number" id="user_number" placeholder="Number" />
			<label><%= number != null ? number : "" %></label>
		<div>
			<a href="/step/first.kitty">back</a> 
			<a href="javascript:{}" onclick="document.forms['second'].submit()">next step</a>
		</div>
	</form:form>
</body>
</html>
