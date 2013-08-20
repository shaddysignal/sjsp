<!DOCTYPE html5>
<html>
<head>
<title>Third step</title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />
</head>
<body>
	<form id="third" action="next_step">
		<input type="text" name="user[region]" id="user_region"
			placeholder="Region" /> 
		<input type="text" name="user[city]"
			id="user_city" placeholder="City" /> 
		<input type="text"
			name="user[street]" id="user_street" placeholder="Street" /> 
		<a href="javascript:history.back()">back</a> 
		<a href="javascript:{}"
			onclick="document.forms['third'].submit()">next step</a>
	</form>
	<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
