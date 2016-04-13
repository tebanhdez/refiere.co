package co.refiere.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import co.refiere.resources.util.HibernateUtil;

public class RefierePlanTest {
  @Test
  public void testCreateRefierePlan() {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
  
    RefierePlan plan = new RefierePlan("Plan Básico", 34, 2, null, 500, "test@test.com", (byte) 0, null);
    session.save(plan);
     
    session.getTransaction().commit();
    session.close();
  
  }
}