package co.refiere.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.models.*;
import co.refiere.services.mailer.ResourceManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;
import co.refiere.resources.base.LoginRequest;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class AuthenticationResourceTest extends JerseyTest{

    private static Properties properties = null;

    static{
        properties = new Properties();
        try {
            properties.load(ResourceManager.getResourceAsInputStream("refiere.properties"));
        } catch (IOException exception) {
            fail("Cannot load refiere.properties file");
        }
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(AuthenticationResource.class);      
    }

    RefiereUserDao userDao = new RefiereUserDao();
    RoleDao role = new RoleDao();
    RefiereCompanyDao companyDao = new RefiereCompanyDao();
    RefiereUserCompanyRelationDao userCompanyRelationDao = new RefiereUserCompanyRelationDao();

    Company testCompany = new Company();
    SimpleUser testSysUser = new SimpleUser();
    SimpleUser testAdminUser = new SimpleUser();
    SimpleUser testSimpleUser = new SimpleUser();
    SimpleUser testSimpleOrphanUser = new SimpleUser();
    SimpleUser testAccUser = new SimpleUser();
    UserCompany adminUserRelation = new UserCompany();
    UserCompany simpleUserRelation = new UserCompany();
    UserCompany accUserRelation = new UserCompany();

    @Before
    public void createTestUser(){

        testCompany.setName("RefiereCompanyTest::testCreateRefiereCompany");
        testCompany.setEmail("test@email.com");
        testCompany.setAddress("Address");
        testCompany.setPhone("+506 8989 8989");
        companyDao.save(testCompany);

        UserRoles sysUserRole = role.findByRoleId(DefaultUserRole.SYS.getId());
        UserRoles adminUserRole = role.findByRoleId(DefaultUserRole.ADMIN.getId());
        UserRoles simpleUserRole = role.findByRoleId(DefaultUserRole.USER.getId());
        UserRoles accountingUserRole = role.findByRoleId(DefaultUserRole.ACCAD.getId());

        Assert.assertNotNull("Admin role not found", sysUserRole);
        Assert.assertNotNull("Admin role not found", adminUserRole);
        Assert.assertNotNull("Admin role not found", simpleUserRole);
        Assert.assertNotNull("Admin role not found", accountingUserRole);

        testSysUser.setLogin("testSys");
        testSysUser.setPassword("testPass");
        testSysUser.setUserRoles(sysUserRole);

        testAdminUser.setLogin("testAdmin");
        testAdminUser.setPassword("testPass");
        testAdminUser.setUserRoles(adminUserRole);
        adminUserRelation.setCompany(testCompany);
        adminUserRelation.setSimpleUser(testAdminUser);

        testSimpleUser.setLogin("testSimple");
        testSimpleUser.setPassword("testPass");
        testSimpleUser.setUserRoles(simpleUserRole);
        simpleUserRelation.setCompany(testCompany);
        simpleUserRelation.setSimpleUser(testSimpleUser);

        testAccUser.setLogin("testAcc");
        testAccUser.setPassword("testPass");
        testAccUser.setUserRoles(accountingUserRole);
        accUserRelation.setCompany(testCompany);
        accUserRelation.setSimpleUser(testAccUser);

        testSimpleOrphanUser.setLogin("orphan");
        testSimpleOrphanUser.setPassword("testPass");
        testSimpleOrphanUser.setUserRoles(simpleUserRole);

        userDao.save(testSysUser);
        userDao.save(testAdminUser);
        userDao.save(testSimpleUser);
        userDao.save(testAccUser);
        userDao.save(testSimpleOrphanUser);
        userCompanyRelationDao.save(adminUserRelation);
        userCompanyRelationDao.save(simpleUserRelation);
        userCompanyRelationDao.save(accUserRelation);
    }

    @Test
    public void sysUserAuthenticatedTest() {

        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(testSysUser.getLogin());
        credentials.setPassword(testSysUser.getPassword());
        
        final Response confirmationResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));
        assertEquals(200, confirmationResponse.getStatus());

        String jsonResponseString = confirmationResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertEquals(DefaultUserRole.SYS.getIdentifier(), object.getString("RoleIdentifier"));
        JsonObject companyJson = object.getJsonObject("company");
        Assert.assertEquals("Company name mismatch", properties.getProperty("refiere.company.name"), companyJson.getString("name"));
        Assert.assertEquals("Company address mismatch", properties.getProperty("refiere.company.address"), companyJson.getString("address"));
        Assert.assertEquals("Company email mismatch", properties.getProperty("refiere.company.email"), companyJson.getString("email"));
        Assert.assertEquals("Company phone mismatch", properties.getProperty("refiere.company.phone"), companyJson.getString("phone"));
    }

    @Test
    public void adminUserAuthenticatedTest() {

        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(testAdminUser.getLogin());
        credentials.setPassword(testAdminUser.getPassword());

        final Response confirmationResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));
        assertEquals(200, confirmationResponse.getStatus());

        String jsonResponseString = confirmationResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertEquals(DefaultUserRole.ADMIN.getIdentifier(), object.getString("RoleIdentifier"));
        JsonObject companyJson = object.getJsonObject("company");
        Assert.assertEquals("Company name mismatch", testCompany.getName(), companyJson.getString("name"));
        Assert.assertEquals("Company address mismatch", testCompany.getAddress(), companyJson.getString("address"));
        Assert.assertEquals("Company email mismatch", testCompany.getEmail(), companyJson.getString("email"));
        Assert.assertEquals("Company phone mismatch", testCompany.getPhone(), companyJson.getString("phone"));
    }

    @Test
    public void simpleUserAuthenticatedTest() {

        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(testSimpleUser.getLogin());
        credentials.setPassword(testSimpleUser.getPassword());

        final Response confirmationResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));
        assertEquals(200, confirmationResponse.getStatus());

        String jsonResponseString = confirmationResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertEquals(DefaultUserRole.USER.getIdentifier(), object.getString("RoleIdentifier"));
        JsonObject companyJson = object.getJsonObject("company");
        Assert.assertEquals("Company name mismatch", testCompany.getName(), companyJson.getString("name"));
        Assert.assertEquals("Company address mismatch", testCompany.getAddress(), companyJson.getString("address"));
        Assert.assertEquals("Company email mismatch", testCompany.getEmail(), companyJson.getString("email"));
        Assert.assertEquals("Company phone mismatch", testCompany.getPhone(), companyJson.getString("phone"));
    }

    @Test
    public void accountingUserAuthenticatedTest() {

        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(testAccUser.getLogin());
        credentials.setPassword(testAccUser.getPassword());

        final Response confirmationResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));
        assertEquals(200, confirmationResponse.getStatus());

        String jsonResponseString = confirmationResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertEquals(DefaultUserRole.ACCAD.getIdentifier(), object.getString("RoleIdentifier"));
        JsonObject companyJson = object.getJsonObject("company");
        Assert.assertEquals("Company name mismatch", testCompany.getName(), companyJson.getString("name"));
        Assert.assertEquals("Company address mismatch", testCompany.getAddress(), companyJson.getString("address"));
        Assert.assertEquals("Company email mismatch", testCompany.getEmail(), companyJson.getString("email"));
        Assert.assertEquals("Company phone mismatch", testCompany.getPhone(), companyJson.getString("phone"));
    }

    @Test
    public void simpleOrphanUserAuthenticatedTest() {

        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(testSimpleOrphanUser.getLogin());
        credentials.setPassword(testSimpleOrphanUser.getPassword());

        final Response noCompanyFoundResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));
        assertEquals(204, noCompanyFoundResponse.getStatus());
    }

    @After
    public void deleteTestUsers(){

        userCompanyRelationDao.deleteRelation(adminUserRelation);
        userCompanyRelationDao.deleteRelation(simpleUserRelation);
        userCompanyRelationDao.deleteRelation(accUserRelation);

        userDao.deleteUser(testSysUser);
        userDao.deleteUser(testAdminUser);
        userDao.deleteUser(testSimpleUser);
        userDao.deleteUser(testAccUser);
        userDao.deleteUser(testSimpleOrphanUser);
        companyDao.deleteCompany(testCompany);
    }
}
