package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import utlity.Utlity;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateToDoServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BigInteger id = BigInteger.valueOf(Integer.parseInt(request.getParameter("id").trim()));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String userIDString = request.getParameter("userId");
			Part inputFilePart = (Part) request.getPart("fileToUpload");

			Boolean isFileAvailableSoSave = inputFilePart.getSize() > 0;
			String directoryPath = Utlity.createSaveFileAndDirectory("toDoWithImage", userIDString, inputFilePart,
					isFileAvailableSoSave, false);
			System.out.println("directoryPath" + directoryPath);
			
			ToDoNote todo = new ToDoNote(title, content, userIDString, directoryPath, new Date());

			Session sess = DBConnection.getFactory().openSession();
			Transaction tr = sess.beginTransaction();

			ToDoNote updateIt = sess.get(ToDoNote.class, id);
			updateIt.setTitle(title);
			updateIt.setContent(content);
			updateIt.setAddedDate(new Date());
			tr.commit();
			sess.close();
			System.out.println(todo);
			response.sendRedirect("showAllToDo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
