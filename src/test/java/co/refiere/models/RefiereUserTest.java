package co.refiere.models;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;

public class RefiereUserTest {

    String SYS_LOGIN = "companyAdmin";//UUID.randomUUID().toString().substring(0, 18);
    String SYS_PASS  = "@dm1n";
    @Test
    public void testCreateCompanyAdminUser() {

        RoleDao role = new RoleDao();
        UserRoles userRole = role.findByRoleId(1);
        Assert.assertNotNull("Role not found",userRole);
        
        SimpleUser adminUser = new SimpleUser();
        //TODO: Provide Random integer - modify RefiereUser.dbm.xml to assign
        //newUser.setId();
        adminUser.setLogin(SYS_LOGIN);
        adminUser.setPassword(SYS_PASS);
        adminUser.setUserRoles(userRole);
        
        RefiereUserDao userDao = new RefiereUserDao();
        System.out.println(String.format(">>> Saving AdminUser: %s", SYS_LOGIN));
        userDao.save(adminUser);

        SimpleUser user = userDao.findByLogin(SYS_LOGIN);
        Assert.assertNotNull("User not found",user);
        Assert.assertTrue(user.getLogin().equals(SYS_LOGIN));
        Assert.assertTrue(user.getPassword().equals(SYS_PASS));
    }

}
