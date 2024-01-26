<%@page import="java.math.BigInteger"%>
<%@page import="entities.ToDoNote"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="connection.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="common/style.css">
		<title>Update | To Do Hibernate</title>
	</head>
<body>
	<%@include file="common/navbar.jsp"%>
	<%
	BigInteger id = BigInteger
			.valueOf(Integer.parseInt(request.getParameter("id").trim()));
	Session sess = DBConnection.getFactory().openSession();
	Transaction tx = sess.beginTransaction();
	ToDoNote updateToDo = (ToDoNote) sess.get(ToDoNote.class, id);
	%>
	<div class="main-content">
		<h2>Update To-Do</h2>
		<form method="post" action="UpdateToDoServlet" enctype="multipart/form-data">
			<input type="hidden" value="userId01" name="userId">
			<div class="form-group">
			<input type="hidden" name="id" value="<%=updateToDo.getId() %>">
				<label for="title">Title</label> <input type="text" class="form-control" name="title" placeholder="Enter title" required="required" value="<%=updateToDo.getId()%>">
			</div>
			<div class="form-group">
				<label for="contnet">Another label</label>
				<textarea class="form-control" name="content" cols="9" required="required"><%=updateToDo.getContent()%></textarea>
			</div>
			<div>
			<%
			if(updateToDo.getFileUrlResource() != null) {
			%>
			Exisiting image:<br>
			<img height="140px" width="200px" style="box-shadow: 0 0 5px gray; margin: 5px" alt="uploaded image" src=<%=updateToDo.getFileUrlResource() %>>
			<%	
			}
			%>
			<label for="fileToUpload">New Image: </label>
			<input type="file" name="fileToUpload" placeholder="Upload Your image" />
			</div>
			<input type="submit" class="btn btn-primary" value="Update" />
		</form>
	</div>
	<%
	tx.commit();
	sess.close();
	%>
	<%@include file="common/footer.jsp"%>
</body>
</html>
