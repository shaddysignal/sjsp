<!DOCTYPE html5>
<html>
<head>
	<title>Start page</title>
	<%@include file="chunks/head.jsp" %>
</head>
<body>
	<h2>Hello Stranger!</h2>
	<p>Want to be recognized?</p>
	<a href="first.kitty" class="button">Let's go!</a>
	<p>Or we already met?</p>
	<form id="login_form" action="define.kitty">
		<input type="text" id="user_email" name="user[email]" placeholder="email" /> 
		<input type="password" id="user_password" name="user[password]" placeholder="Password" />
		<a href="javascript:{}" onclick="document.forms['login_form'].submit()" class="button">Define you</a>
	</form>
</body>
</html>
