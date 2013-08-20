<!DOCTYPE html5>
<html>
<head>
<title>Second step</title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />
</head>
<body>
	<form id="second" action="next_step">
		<input type="text" name="user[birthdate]" id="user_birthdate" placeholder="Bithdate" />
		<input type="text" size="4" name="user[serial]" id="user_serial" placeholder="Serial" />
		<input type="text" size="6" name="user[number]" id="user_number" placeholder="Number" />
		<a href="javascript:history.back()">back</a> 
		<a href="javascript:{}" onclick="document.forms['second'].submit()">next step</a>
	</form>
	<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
