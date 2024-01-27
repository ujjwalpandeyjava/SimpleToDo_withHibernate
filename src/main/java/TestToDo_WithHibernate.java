import java.util.List;

import org.hibernate.Session;

import connection.DBConnection;
import entities.ToDoNote;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class TestToDo_WithHibernate {
	public static void main(String[] args) {
		Session session = DBConnection.getFactory().openSession();

		// Way 1
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ToDoNote> criteria = builder.createQuery(ToDoNote.class);
		criteria.from(ToDoNote.class);
		List<ToDoNote> products = session.createQuery(criteria).getResultList();
		System.out.println(products);

		// Way 2
		List<ToDoNote> products2 = session.createQuery("SELECT a FROM todonote a", ToDoNote.class).getResultList();
		System.out.println("Size: " + products2.size() + " | " + products2);

		// Way 3
		List<ToDoNote> resultList = session
				.createQuery("select a From todonote a order by a.addedDate desc", ToDoNote.class).getResultList();
		System.out.println("Size: " + resultList.size() + " | " + resultList);
	}
}