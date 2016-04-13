package co.refiere.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import co.refiere.resources.util.HibernateUtil;

public class RefiereLapseTest {
  @Test
  public void testCreateRefiereLapse() {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
  
    RefiereLapse lapse = new RefiereLapse("Semanalmente");
    session.save(lapse);
     
    session.getTransaction().commit();
    session.close();
  
  }
}
