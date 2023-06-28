package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class ToDoWithImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ToDoWithImage() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userName = request.getParameter("loggedInUserName");
		String titleText = request.getParameter("titleText");
		Part inputFilePart = request.getPart("photo");

		String uploadingFile = "";
		String directoryPath = "/uploadedFiles/tableName/" + userName; // Dynamic saving directory
		String originalFileName = getFileName(inputFilePart);
		checkAndMakeTheDirectory(new File(directoryPath));

		try {
			OutputStream out = null;
			InputStream filecontent = null;
			int read = 0;

			out = new FileOutputStream(new File(directoryPath + File.separator + originalFileName));
			filecontent = inputFilePart.getInputStream();
			final byte[] readFile = new byte[1024]; // 1MB buffer
			while ((read = filecontent.read(readFile)) != -1) {
				out.write(readFile, 0, read);
				uploadingFile = directoryPath + "/" + originalFileName;
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todohibernate", "root",
					"Goldenstar@123");
			String query = "INSERT INTO name(name, photourl) value(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, titleText);
			ps.setString(2, uploadingFile);
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

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader: " + partHeader);
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	private File checkAndMakeTheDirectory(File theDirectory) {
		System.out.println("Creating this: " + theDirectory.getAbsolutePath());
		if (theDirectory.exists())
			System.out.println("Folder already exists");
		if (theDirectory.mkdirs())
			System.out.println("Folder created");
		System.out.println("The Directory: " + theDirectory);
		return theDirectory;
	}
}