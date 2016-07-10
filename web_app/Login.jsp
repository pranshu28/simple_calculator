<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Login Page</title>
	</head>
	<body>
		<h3>Login Here</h3>
		<form action="LoginServlet" method="post">
			<br>
			<% if (request.getParameter("click") != null) {%>
				<% if (((String)request.getParameter("click")).matches("Reset")) {%> 
						*Reset*
				<%} else if (((String)request.getParameter("click")).matches("Logout")) { %> 
						<% session.setAttribute("allow",null); %> 
						*Logout Successful*
				<%} else if (((String)request.getParameter("click")).matches("Login")) { %>
						Invalid User ID or Password!!<br>
				<%} %><br><p>Enter Here.<br></p>
			<% } %>
			<table>
				<tr align="center">
					<td>User ID : 
					<td><input type="text" name="user_id" placeholder="Username or Email" required/>
				</tr>
				<tr align="center">
					<td>Password : 
					<td><input type="password" name="password" required/>
				</tr>
				<tr align="center">
					<td>
					<td><input type="submit" name="click" value="Login"> <input type="submit" name="click" value="Reset">
				</tr>
			</table> 
		</form>
	</body>
</html>
