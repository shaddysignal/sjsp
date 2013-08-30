<%@page import="java.util.Map"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String birthdate = "";
	String serial = "";
	String number = "";
	String serialnumber = "";
	if((Map<String,String>)request.getAttribute("errors") != null) {
		birthdate = ((Map<String,String>)request.getAttribute("errors")).get("phonenumber");
		serial = ((Map<String,String>)request.getAttribute("errors")).get("email");
		number = ((Map<String,String>)request.getAttribute("errors")).get("password");
		serialnumber = ((Map<String,String>)request.getAttribute("errors")).get("repeatpassword");
	}
%>
<!DOCTYPE html5>
<html>
<head>
	<title>Second step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="second" action="next_step.kitty" method="POST" commandName="user">
		<form:hidden path="id" />
		<form:input path="birthdate" id="user_birthdate" placeholder="Bithdate" />
			<label><%= birthdate != null ? birthdate : "" %></label>
		<form:input path="serial" id="user_serial" placeholder="Serial" />
			<label><%= serial != null ? serial : "" %></label>
			<label><%= serialnumber != null ? serialnumber : "" %></label>
		<form:input path="number" id="user_number" placeholder="Number" />
			<label><%= number != null ? number : "" %></label>
		<div>
			<a href="javascript:history.back()">back</a> 
			<a href="javascript:{}" onclick="document.forms['second'].submit()">next step</a>
		</div>
	</form:form>
</body>
</html>
