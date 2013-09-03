<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
	<title>Third step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="third" action="/finale.kitty" method="POST" commandName="user" autocomplete="true">	
		<form:hidden path="id" />
		<form:input path="region" id="user_region" placeholder="Region" />
		<form:input path="city" id="user_city" placeholder="City" />
		<form:input path="street" id="user_street" placeholder="Street" />
		<div> 
			<a href="/step/second.kitty">back</a>
			<a href="javascript:{}" onclick="document.forms['third'].submit()">final</a>
		</div>
	</form:form>
</body>
</html>
