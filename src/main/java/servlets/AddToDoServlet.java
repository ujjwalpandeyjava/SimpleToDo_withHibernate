package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToDoServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			ToDoNote todo = new ToDoNote(title, content, new Date());

			// Adding in database with the help of hibernate.
			Session sess = DBConnection.getFactory().openSession();
			Transaction tr = sess.beginTransaction();
			sess.save(todo);
			tr.commit();
			sess.close();
			// To print data on console.
			// System.out.println(todo);
			// To print data on web page.
			// response.setContentType("text/html");
			PrintWriter pr = response.getWriter();
			pr.println("<h3>Data added successfully!!</h3>");
			// wait(2000);
			response.sendRedirect("showAllToDo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}