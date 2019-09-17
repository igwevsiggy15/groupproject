<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="entities.User"%>
<%@ page import="db.HibernateUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@include file="/css/site_styles.css"%>
</style>

<link href='http://fonts.googleapis.com/css?family=Great+Vibes'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<title>Gallery</title>
</head>
<body>
<div id="controls">
			<div class="option">
				<form action="upload.jsp">
					<input type="submit" value="Upload a file">
				</form>
			</div>
			<div class="option">
				<form action="home.jsp">
					<input type="hidden" name="type" value="search">
					<input type="text" name="filter"> 
					<input type="submit" value="Search">
				</form>
			</div>
			<div class="option">
				<form action="home.jsp">
				<input type="hidden" name="type" value="sort">
					<select name="filter">
						<option value="Other">Other</option>
						<option value="Events">Events</option>
						<option value="People">People</option>
						<option value="Animals">Animals</option>
						<option value="Places">Places</option>
						<option value="Food">Food</option>
						<option value="Things">Things</option>
					</select> 
					<input type="submit" value="Filter by category">
				</form>
			</div>
			<div class="option">
				<form action="Logout" method="post">
					<input type="submit" value="Logout" />
				</form>
			</div>
		</div>

	<div class="gallery_container">
		<h1 id="first">
			Welcome
			<%=request.getSession().getAttribute("name")%>!
		</h1>
		<h2>This is your super-awesome gallery</h2>
		
		<%
			response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
			response.setHeader("Pragma", "no-cache"); //HTTP 1.0
			response.setDateHeader("Expires", 0);
			//prevents caching at the proxy server

			Integer userId = (Integer) request.getSession().getAttribute("user"); // user ID
			if (userId == null) {
				response.sendRedirect("index.jsp");
				return;
			}
			String images="";
			String type = request.getParameter("type");
			if (type == null) {
				System.out.println("Type: null");
				images=HibernateUtil.allImages(userId);
			} else if (type.equals("search")) { 
				System.out.println("Type: search");
				images=HibernateUtil.searchImages(userId, request.getParameter("filter"));
			} else if (type.equals("sort")) { 
				System.out.println("Type: sort");
				images=HibernateUtil.sortImages(userId, request.getParameter("filter"));
			} else{
				out.print("Done goofed");
			}
		%>

		<div class="gallery">
			<%=images%>
		</div>
	</div>

	<script>
		$(function() {
			$(".resizable").resizable({
				autoHide : true,
				aspectRatio : true
			});
		});
	</script>
</body>
</html>