package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;
public class AddToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToDoServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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