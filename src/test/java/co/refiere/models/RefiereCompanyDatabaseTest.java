package co.refiere.models;

import org.junit.Test;

import co.refiere.dao.CompanyDatabaseDao;

public class RefiereCompanyDatabaseTest {
    
    @Test
    public void testCreateRefiereCompanyDatabaseStructure() {
        
        CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
        
        CompanyDatabase companyDatabase = new CompanyDatabase();
        
        companyDatabase.setName("New Test Database");
        
        companyDatabaseDao.save(companyDatabase);
    }
}
