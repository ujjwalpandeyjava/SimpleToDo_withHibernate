<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="common/style.css">
<title>Add | To Do Hibernate</title>
</head>
<body>
	<%@include file="common/navbar.jsp"%>
	<div class="main-content">
		<h2>Add New To Do in list</h2>
		<form method="post" action="AddToDoServlet">
			<div class="form-group">
				<label for="title">Title</label>
				 <input type="text" class="form-control" name="title"
					placeholder="Enter title" required="required">
			</div>
			<div class="form-group">
				<label for="contnet">Another label</label>
					<textarea class="form-control" name="content" cols="8" required="required"></textarea>
			</div>
			    <input type="submit" class="btn btn-primary" value="Add"/>
			
		</form>
	</div>

	<%@include file="common/footer.jsp"%>
</body>
</html>
