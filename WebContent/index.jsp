<html>
<head><title>Login</title></head>
<body>
<h3 align="center">Welcome to Meowspital</h3>

<form align="center" action="AdmissionControllerServlet" method ="POST">
<input type="hidden" name="command" value="LOGIN" />
		
Username : <input type="text" name="username" />

Password : <input type="text" name="password" />

<input type="submit" value="Submit" 
	class="add-student-button"/>
</form>

</body>

</html>