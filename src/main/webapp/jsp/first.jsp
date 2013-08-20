<!DOCTYPE html5>
<html>
<head>
<title>First step</title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />
</head>
<body>
	<form id="first" action="next_step">
		<input type="text" name="user[firstName]" id="user_firstName"
			placeholder="First Name" /> 
		<input type="text"
			name="user[secondName]" id="user_secondName"
			placeholder="Second Name" /> 
		<input type="text"
			name="user[fathersName]" id="user_fathersName"
			placeholder="Fathers Name" />
		<input type="text"
			name="user[phonenumber]" id="user_phonenumber"
			placeholder="Phonenumber" />
		<input type="text" name="user[email]"
			id="user_email" placeholder="Email" /> 
		<input type="password"
			name="user[password]" id="user_password" placeholder="Password" />
		<input
			type="password" id="user_rpassword" placeholder="Repeat Password" />
		<a href="/">cancel</a> <a href="javascript:{}"
			onclick="document.forms['first'].submit()">next step</a>
	</form>
	<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
