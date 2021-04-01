<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="entities.ToDoNote"%>
<%@page import="connection.DBConnection"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="common/style.css">
<title>Show all | To Do Hibernate</title>
</head>
<body>
	<%@include file="common/navbar.jsp"%>
	<div class="main-content">
		<div>
			<h2>This is your to do list</h2>
		</div>
		<div
			class="d-flex flex-row bd-highlight mb-3 flex-wrap justify-content-center mx-2 p-4 add-shadow w-100">
			<%
			Session s = DBConnection.getFactory().openSession();
			Query q = s.createQuery("From ToDoNote order by addedDate desc");
			List<ToDoNote> l = q.list();
			if (l.isEmpty()) {
			%>
			<div class="col-12 justify-content-center mx-auto p-4 add-shadow  text-center">
				<h3 class="mx-auto justify-content-center">You don't have any
					to do add new here</h3>
				<a href="addToDo.jsp" class="btn btn-primary">Add new note</a>
			</div>
			<%
			} else {

			for (ToDoNote td : l) {
			%>
			<div class="col-12 col-md-6 col-lg-4 p-3 text-center mb-3">
				<div class="text-end px-3"><%=td.getAddedDate()%></div>
				<%=td.getTitle()%>
				<hr>
				<%=td.getContent()%>
				<hr>
				<a class="btn btn-danger"
					href="DeleteToDoServlet?id=<%=td.getId()%>" role="button">Delete</a>
				<a class="btn btn-primary" href="update.jsp?id=<%=td.getId() %>" role="button">Edit</a>
			</div>
			<%
			}
			}
			s.close();
			if(!l.isEmpty()){
			%>
			<div class="col-12 justify-content-center mx-auto p-4 add-shadow text-center">
				<a class="mx-auto btn btn-outline-info"
					href="addToDo.jsp">Add new note</a>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<%@include file="common/footer.jsp"%>
</body>
</html>