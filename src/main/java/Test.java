import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todohibernate", "root",
					"Goldenstar@123");
			PreparedStatement psPreparedStatement = con.prepareStatement("SELECT * FROM name ORDER BY ID DESC LIMIT 1");
			ResultSet executeQuery = psPreparedStatement.executeQuery();
			System.out.println(executeQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
