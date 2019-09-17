<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/css/site_styles.css" %></style>
<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
<div class="gallery_container">

	<h1>Registration</h1>
	
	<div class="dialog">
		<form action="Register" method="post">
			<table>
			<tr>
				<td>Name</td><td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Email Address</td><td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Password</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td><td><input type="submit"></td>
			</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>