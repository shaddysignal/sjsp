<!DOCTYPE html5>
<html>
<head>
<title>Cabinet</title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />
</head>
<body>
	<h2>Hello <%= session.getCreationTime() %>!</h2>
	<div>
		<p>You can delete your self by clicking on</p>
		<a href="delete" onclick="confirm('Are you sure?')">here</a>
	</div>
	<div>
		<p>Or you can update data by clicking on</p>
		<a href="first_step">here</a>
	</div>
</body>
</html>
