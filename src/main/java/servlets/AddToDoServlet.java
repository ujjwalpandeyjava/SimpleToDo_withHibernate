package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import entities.ToDoNote;
import enums.LocalPaths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

//			System.out.println(title + content + userIDString + inputFilePart.getSize());

			Boolean isFileAvailableSoSave = inputFilePart.getSize() > 0;
			String directoryPath = Utlity.createSaveFileAndDirectory("toDoWithImage", userIDString, inputFilePart,
					isFileAvailableSoSave, false);
			System.out.println("directoryPath: " + directoryPath);
			System.out.println("directoryPath2: " + directoryPath.replace("\\", "/"));
//			System.out.println("LocalPaths.UPLOAD_PATH.getValue(): " + LocalPaths.UPLOAD_PATH.getValue());
//			System.out.println(LocalPaths.UPLOAD_PATH.getValue().replaceAll("/", "\\"));
//			System.out.println(directoryPath.replaceAll(LocalPaths.UPLOAD_PATH.getValue().replace("\\", "\\"), "/"));
//			directoryPath = LocalPaths.UPLOAD_PATH.getValue()		+ directoryPath.replaceFirst(LocalPaths.UPLOAD_PATH.getValue(), "\\");
//			System.out.println("directoryPath 2: " + directoryPath);

			ToDoNote todo = new ToDoNote(title, content, userIDString, directoryPath, new Date());

//			System.out.println(todo);

			// Adding in database with the help of hibernate.
			Session sess = DBConnection.getFactory().openSession();
			Transaction tr = sess.beginTransaction();
			sess.save(todo);
			tr.commit();
			sess.close();
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