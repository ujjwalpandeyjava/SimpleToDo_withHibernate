package servlets;

import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import utlity.Utlity;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
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
			String userIDString = request.getParameter("userId");
			Part inputFilePart = request.getPart("fileToUpload");

			Boolean isFileAvailableSoSave = inputFilePart.getSize() > 0;
			String directoryPath = Utlity.createSaveFileAndDirectory("toDoWithImage", userIDString, inputFilePart,
					isFileAvailableSoSave, false);
			ToDoNote todo = new ToDoNote(title, content, userIDString, directoryPath, new Date());

			// Adding in database with the help of hibernate.
			Session sess = DBConnection.getFactory().openSession();
			Transaction tr = sess.beginTransaction();
			sess.persist(todo);
			tr.commit();
			sess.close();
			HttpSession session = request.getSession();
			session.setAttribute("add-success", "Added successfully!");
			response.sendRedirect("showAllToDo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}