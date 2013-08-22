<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html5>
<html>
<head>
	<title>Second step</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<form:form id="second" action="next_step.kitty" method="POST" commandName="user">
		<form:input path="birthdate" id="user_birthdate" placeholder="Bithdate" />
		<form:input size="4" path="serial" id="user_serial" placeholder="Serial" />
		<form:input size="6" path="number" id="user_number" placeholder="Number" />
		<a href="javascript:history.back()">back</a> 
		<a href="javascript:{}" onclick="document.forms['second'].submit()">next step</a>
	</form:form>
	<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
