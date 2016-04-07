package co.refiere.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.tutorial.util.HibernateUtil;
import org.junit.Test;

public class RefiereUserTest {

	@Test
	public void testCreateRefiereUser() {
	  
	  SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();
    session.beginTransaction();
    
    RefiereUser user = new RefiereUser("Steve", "Balmer");
    session.save(user);
     
    session.getTransaction().commit();
    session.close();

	}

}
