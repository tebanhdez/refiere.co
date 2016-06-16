package co.refiere.resources;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;
import co.refiere.models.Company;
import co.refiere.models.CompanyDatabase;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserRoles;
import co.refiere.resources.base.DatabaseObjectData;
import co.refiere.resources.util.DatabaseResource;

public class CompanyDatabaseTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(DatabaseResource.class);
    }
    
    RefiereCompanyDao companyDao = new RefiereCompanyDao();
    Company testCompany = new Company();
    CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
    CompanyDatabase testCompanyDatabase = new CompanyDatabase();
    RefiereUserDao userDao = new RefiereUserDao();
    SimpleUser user = new SimpleUser();
    RoleDao roleDao = new RoleDao();
    RefiereUserCompanyRelationDao userCompanyRelationDao = new RefiereUserCompanyRelationDao();
    
    @Before
    public void createTestCompany() {
        
        testCompany.setName("RefiereCompanyTest");
        testCompany.setEmail("test@email.com");
        testCompany.setAddress("Address");
        testCompany.setPhone("+506 8989 8989");
        companyDao.save(testCompany);
        
        testCompanyDatabase.setName("Test2 Database");
        testCompanyDatabase.setCompany_id(testCompany.getId());
        companyDatabaseDao.save(testCompanyDatabase);
        
        user.setLogin("UserTest");
        user.setPassword("Password");
        userDao.save(user);
        
        UserRoles roleID = roleDao.findByRoleId(11);
    }
    
    @Test
    public void testFindAllDatabasesByCompany() {
        CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
        List<CompanyDatabase> databasesRefs = companyDatabaseDao.findAllDatabasesByCompany(user.getLogin());
        Assert.assertNotNull("Reference to database not found", databasesRefs);
    }
    
    @After
    public void deleteTests(){
        companyDao.deleteCompany(testCompany);
        companyDatabaseDao.deleteCompanyDatabase(testCompanyDatabase);
        userDao.deleteUser(user);
    }
    
}
