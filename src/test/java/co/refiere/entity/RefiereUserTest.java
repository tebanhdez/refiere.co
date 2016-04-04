package co.refiere.entity;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class RefiereUserTest {

	@Test
	public void testCreateRefiereUser() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from RefiereUser");
		List list = query.list();
		Assert.assertTrue(list.size() == 0);
		session.beginTransaction();
 
		RefiereUser user = new RefiereUser("refiereAdmin");
		session.save(user);
 
		session.getTransaction().commit();
		
		list = query.list();
		Assert.assertTrue(list.size() == 1);

		session.close();
	}

}
