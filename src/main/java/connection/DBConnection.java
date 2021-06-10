package connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {

	public DBConnection() {
	}
	// I used static so that I can call this function without making an object
	// and calling this method like an function
	public static SessionFactory factory;
	public static SessionFactory getFactory() {
		if (factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	public void closeFactory() {
		if (factory.isOpen())
			factory.close();
	}

}
