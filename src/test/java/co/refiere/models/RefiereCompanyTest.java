package co.refiere.models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.RefiereCompanyDao;

public class RefiereCompanyTest {

    RefiereCompanyDao companyDao = new RefiereCompanyDao();
    int testCompanyId;

    @Before
    public void createTestData(){
        Company testCompany = new Company();
        testCompany.setName("RefiereCompanyTest::testCreateRefiereCompany");
        testCompany.setEmail("test@email.com");
        testCompany.setAddress("Address");
        testCompany.setPhone("+506 8989 8989");
        companyDao.save(testCompany);
        testCompanyId = testCompany.getId();
    }

    @Test
    public void testCreateRefiereCompany() {
        Company testCompany = companyDao.findCompanyById(testCompanyId);
        Assert.assertNotNull("Company Not saved", testCompany);
        Assert.assertNotNull("Invalid company", testCompany.getId());
    }

    @After
    public void deleteTestCompany(){
        Company testCompany = companyDao.findCompanyById(testCompanyId);
        companyDao.deleteCompany(testCompany);
    }
}
