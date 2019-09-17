<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>

<style><%@include file="/css/site_styles.css" %></style>

<title>Gallery Landing</title>
</head>

<body>

<div class="gallery_container">
<noscript><h1>You should enable Javascript to enjoy the full awesomeness of this page</h1></noscript>

<h1>Welcome to the Web Gallery</h1>

<%
	//Redirect if already logged in.
	if (request.getSession().getAttribute("loggedIn") != null && request.getSession().getAttribute("loggedIn").equals(true)) {
		response.sendRedirect("home.jsp");
		
		//request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	else {
		request.setAttribute("loggedIn", Boolean.valueOf(false));
	}
%>

<h2>You should log in</h2>
<div class="dialog">
	<form action="Login" method="post">
	<table>
		<tr>
			<td><p>Email Address</p></td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td><p>Password</p></td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit"></td>
		</tr>
	</table>
	</form>

	<p>Don't have an account yet?  Make one <a href="registration.jsp">here</a></p>
</div>
</div>
</body>
</html>