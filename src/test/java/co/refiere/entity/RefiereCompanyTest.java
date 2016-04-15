package co.refiere.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import co.refiere.resources.util.HibernateUtil;

public class RefiereCompanyTest {
  
//  @Test
  public void testCreateRefiereCompany() {
    
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();
    session.beginTransaction();

    //RefiereCompany company = new RefiereCompany("Steve", "Balmer", "Microsoft", "LA", "microsoft@outlook.com", "555555555", "Basic");
    //session.save(company);
     
    session.getTransaction().commit();
    session.close();

  }
}
