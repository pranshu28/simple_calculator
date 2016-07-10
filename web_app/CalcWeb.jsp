<%@page import="func.Logic"%>
<%@page import="func.WebCalculator" %>

<%! String digitList[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "+/-"};
	String operatorList[] = {"/", "*", "^", "-", "+", "%"};
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Calculator</title>
	</head>
	<body>	
		<form action="LoginServlet" method="post">
		<% if (session.getAttribute("allow") != null) { %>
		<p>You can use the Calculator here.</p>
		</form>
		<form action="WebCalculator" method="get">	
			<% if (request.getAttribute("editor") == null) {
				request.setAttribute("editor","0");
				request.setAttribute("status","0");
				request.setAttribute("note",""); %>
				<input type="hidden" name="start" value="1">
			<% } %>
			<table>
				<tr>
					<td align="center" style="width:60;height:40">Input :
					<td align="right" style="width:200;height:40"><font size="4"><%= request.getAttribute("editor") %></font>
				</tr>
				<tr>
					<td align="center" style="width:60;height:40">Answer :
					<td align="right" style="width:200;height:40"><font size="3"><%= request.getAttribute("status") %></font>
				</tr>
				<tr align="right">
					<td colspan="2" style="width:260;height:25"><font size="2"><%= request.getAttribute("note") %> </font>
				</tr>
			</table>
			
			<div style="float: left">
			<table>
				<% for (int i=0, digit_i=0; i<4 && digit_i<12; i++) { %>
				<tr>
					<% for (int j=0; j<3; j++) { %>
					<td>
						<input type="submit" style="width:40;height:40" name="clicked" value="<%=digitList[digit_i]%>" onclick ="<% request.setAttribute("clicked", digitList[digit_i]);%>">
						<% digit_i++;%>
					</td>
					<% } %>
				</tr>
				<% } %>
			</table>
			</div>
			
			<div>
			<table>
				<tr>
					<td colspan="2"><input type="submit" style="width:85;height:40" name="clicked" value="<--" onclick = "<%request.setAttribute("clicked", "<--");%>">
					<td><input type="submit" style="width:40;height:40" name="clicked" value="C" onclick = "<%request.setAttribute("clicked", "C");%>">
				</tr>
				<%for (int i=0, operator_i=0; i<2 && operator_i<6; i++) {%>
				<tr>
					<%for (int j=0; j<3; j++) {%>
					<td>
						<input type="submit" style="width:40;height:40" name="clicked" value="<%=operatorList[operator_i]%>" onclick ="<%request.setAttribute("clicked", operatorList[operator_i]);%>">
						<% operator_i++;%>
					</td>
					<%} %>
				</tr>
				<%} %>
				<tr>
					<td colspan="3"><input type="submit" style="width:130;height:40" name="clicked" value="=" onclick = "<%request.setAttribute("clicked", "=");%>">
				</tr>
			</table>
			</div>
			
		</form>
		<br>
		<form action="LoginServlet" method="post">
			Click here to <input type="submit" name="click" value="Logout">
		<%} else { response.sendRedirect("Login.jsp"); } %>
		</form>
	</body>
</html>
