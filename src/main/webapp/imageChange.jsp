<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Save image on dir and path on db</title>
</head>
<style>
.mainContainer{
	max-width: 400px;
	margin-inline: auto;
	padding: 1em;
	background-color: wheat;
	box-shadow: 0 0 5px gray;
}
.container{
	border: 1px solid red;
}
.text-center{
	text-align: center;
}
</style>
<body>
	<div>url: http://localhost:8080/ToDowithHibernate/imageChange.jsp <br></div>
	<div class="container mainContainer">
		<h2 class="text-center">Add your note</h2>
		<form action="ToDoWithImage" method="post" enctype="multipart/form-data">
		<input type="hidden" name="loggedInUserName" value="User 1">
			<div class="mb-3">
				<label for="titleText" class="form-label">Note title</label> 
				<input type="text" name="titleText" value="Test title" class="form-control" aria-describedby="emailHelp">
			</div>
			<label for="photo">File</label><br> 
			<input type="file" name="photo" placeholder="Upload Your Image" /><br>
			<div class="container text-center">
				<button type="submit" class="btn btn-primary text-center ">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>