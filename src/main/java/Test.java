import java.io.File;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import connection.DBConnection;
import entities.ToDoNote;

public class Test {

	public static void main(String[] args) {
		
		
		System.out.println("sfalskfkhj");
		Session s = DBConnection.getFactory().openSession();
		System.out.println("2");
		Query<ToDoNote> q = s.createQuery("From ToDoNote order by addedDate desc");
		System.out.println("3");
		System.out.println(q);
		System.out.println("4");
		List<ToDoNote> l = q.list();
		System.out.println(l.size());
		
		
		/*
		try {
			Path relativePath = Paths.get("../"); // Just before the project
			Path realPath = relativePath.toRealPath();
			System.out.println("Realtive path: " + relativePath + " | RealPath: " + realPath);
			String directoryPath = realPath + "/uploadedFiles/tableName/userName 1";
			System.out.println(directoryPath);
			checkAndMakeTheDirectory(new File(directoryPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Path p1 = Paths.get("../Ecilipse shorthands.txt");
			Path p1RealPath = p1.toRealPath();
			System.out.println("p2RealPath:" + p1RealPath);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	private static File checkAndMakeTheDirectory(File theDirectory) {
		System.out.println("Creating this: " + theDirectory.getAbsolutePath());
		if (theDirectory.exists())
			System.out.println("Folder already exists");
		if (theDirectory.mkdirs())
			System.out.println("Folder created");
		System.out.println("The Directory: " + theDirectory);
		return theDirectory;
	}
}
