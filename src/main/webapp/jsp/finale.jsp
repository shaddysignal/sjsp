<%@page import="com.improveit.simpleapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
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
		<a href="/undefine.kitty" id="undefine" data-userId="<%= user.getId() %>">here</a>
	</div>
	<div>
		<p>Or you can update data by clicking on</p>
		<a href="/first.kitty">here</a>
	</div>
	<script type="text/javascript">
		(function() {
			var undefine = document.getElementById("undefine"),
				onclick = function() {					
					var request = new XMLHttpRequest();
					request.open("delete", document.location.domain + "/undefine.kitty", false);
					request.send();
					document.location.href= document.location.domain + "/";
					return false;
				};
				
			undefine.addEventListener("click", onclick, false);
		})()
	</script>
</body>
</html>
