package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
public class ToDoWithImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ToDoWithImage() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userIDString = request.getParameter("loggedInUserName");
		String titleText = request.getParameter("titleText");
		Part inputFilePart = request.getPart("photo");

		Boolean isFileAvailableSoSave = inputFilePart.getSize() > 0;

		String directoryPath = Utlity.createSaveFileAndDirectory("toDoWithImage", userIDString, inputFilePart, isFileAvailableSoSave, false);
		System.out.println("directoryPath" + directoryPath);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todohibernate", "root",
					"Goldenstar@123");
			String query = "INSERT INTO name(name, photourl) value(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, titleText);
			ps.setString(2, (isFileAvailableSoSave) ? directoryPath : null);
			int i = ps.executeUpdate();
			System.out.println((i == 1) ? "Saved" : "Not saved");

			PreparedStatement psPreparedStatement = con.prepareStatement("SELECT * FROM name ORDER BY ID DESC LIMIT 1");
			boolean execute = psPreparedStatement.execute();
			System.out.println(execute);
			session.setAttribute("data", execute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("imageChange.jsp");
	}
}