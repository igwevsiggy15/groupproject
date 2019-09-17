<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/css/site_styles.css" %></style>
<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<meta charset="ISO-8859-1">
<title>Upload</title>
</head>
<body>
<!-- Once logged on, show photos and functionality -->
<div class="gallery_container">
	<h2>Choose file to upload</h2>
	
	<div class="dialog">
		<form action="FileUploadHandler" method="post" enctype="multipart/form-data" id="upload">
			<label for="file_upload" class="custom_file_upload">
				Choose your file</label>
			<input id="file_upload" type="file" name="file" />
			<input type="submit" value="Upload" />
		</form>
		<br/>
		<select name="category" form="upload">
				<option value="Other">Other</option>
				<option value="Events">Events</option>
				<option value="People">People</option>
				<option value="Animals">Animals</option>
				<option value="Places">Places</option>
				<option value="Food">Food</option>
				<option value="Things">Things</option>
		</select>
	</div>
</div>
</body>
</html>