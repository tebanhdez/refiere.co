package co.refiere.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.resources.util.HibernateUtil;

public class RefiereCompanyTest {


    @Test
    public void testCreateRefiereCompany() {

        Company newCompany = new Company();
        //TODO: Provide Random integer - modify RefiereUser.dbm.xml to assign
        //newUser.setId();
        newCompany.setName("RefiereCompanyTest::testCreateRefiereCompany");
        newCompany.setEmail("test@email.com");

        RefiereCompanyDao companyDao = new RefiereCompanyDao();
        companyDao.save(newCompany);
    }
}
