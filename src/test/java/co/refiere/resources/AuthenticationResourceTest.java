package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserRoles;
import co.refiere.resources.base.LoginRequest;

public class AuthenticationResourceTest extends JerseyTest{
    
    @Override

    protected Application configure() {
        return new ResourceConfig(AuthenticationResource.class);      
    }
    
    String SYS_LOGIN = "root";
    String SYS_PASS  = "@dm1n15tra@t0r";
    RoleDao role = new RoleDao();
    UserRoles userRole = role.findByRoleId(1);
    SimpleUser user;
    
    @Before
    public void setTestUser() {
        user = new SimpleUser();
        
        user.setLogin(SYS_LOGIN);
        user.setPassword(SYS_PASS);
        user.setUserRoles(userRole);
        
        RefiereUserDao refiereUserDao = new RefiereUserDao();
        //refiereUserDao.save(user);
    }

    @Test
    public void userAuthenticationTest() {
        String response = "{\"RoleIdentifier\": \"%s\", \"status\":\"%s\", \"company\": %s}";
        LoginRequest credentials = new LoginRequest();
        credentials.setLogin(SYS_LOGIN);
        credentials.setPassword(SYS_PASS);
        
        final Response confirmationResponse = target().path("v1/auth/login").request().post(Entity.json(credentials));

        assertEquals(200, confirmationResponse.getStatus());
    }
    
    @After
    public void deleteTestUser() {
        RefiereUserDao refiereUserDao = new RefiereUserDao();
        //refiereUserDao.deleteUser(user);
    }

}
