package co.refiere.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import co.refiere.resources.util.HibernateUtil;

public class RefiereUserTest {

//	@Test
	public void testCreateRefiereUser() {
	  
	  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    
    RefiereUser user = new RefiereUser("Steve", "Balmer");
    session.save(user);
     
    session.getTransaction().commit();
    session.close();

	}

}
