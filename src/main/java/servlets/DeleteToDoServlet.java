package servlets;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;

public class DeleteToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DeleteToDoServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BigInteger id = BigInteger.valueOf(Integer.parseInt(request.getParameter("id").trim()));
			Session s = DBConnection.getFactory().openSession();
			Transaction t = s.beginTransaction();
			ToDoNote deleteTD = (ToDoNote) s.get(ToDoNote.class, id);
			s.delete(deleteTD);
			t.commit();
			s.close();
			response.sendRedirect("showAllToDo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
