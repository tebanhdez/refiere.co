package co.refiere.models;

import java.util.UUID;

import org.junit.*;

import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;

public class SimpleUserTest {

    RefiereUserDao userDao = new RefiereUserDao();

    SimpleUser testSysUser = new SimpleUser();
    SimpleUser testAdminUser = new SimpleUser();
    SimpleUser testSimpleUser = new SimpleUser();
    SimpleUser testAccUser = new SimpleUser();

    @Before
    public void createTestUser(){

        RoleDao role = new RoleDao();

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

        testSimpleUser.setLogin("testSimple");
        testSimpleUser.setPassword("testPass");
        testSimpleUser.setUserRoles(simpleUserRole);

        testAccUser.setLogin("testAcc");
        testAccUser.setPassword("testPass");
        testAccUser.setUserRoles(accountingUserRole);

        userDao.save(testSysUser);
        userDao.save(testAdminUser);
        userDao.save(testSimpleUser);
        userDao.save(testAccUser);
    }

    @Test
    public void testCreateCompanyAdminUser() {
        Assert.assertNotNull("Admin user not created", testSysUser);
        Assert.assertNotNull("Admin user not created", testAdminUser);
        Assert.assertNotNull("Admin user not created", testSimpleUser);
        Assert.assertNotNull("Admin user not created", testAccUser);

        Assert.assertTrue("Admin user role invalid", testSysUser.getUserRoles().getRoleIdentifier().compareToIgnoreCase(DefaultUserRole.SYS.getIdentifier()) == 0);
        Assert.assertTrue("Admin user role invalid", testAdminUser.getUserRoles().getRoleIdentifier().compareToIgnoreCase(DefaultUserRole.ADMIN.getIdentifier()) == 0);
        Assert.assertTrue("Admin user role invalid", testSimpleUser.getUserRoles().getRoleIdentifier().compareToIgnoreCase(DefaultUserRole.USER.getIdentifier()) == 0);
        Assert.assertTrue("Admin user role invalid", testAccUser.getUserRoles().getRoleIdentifier().compareToIgnoreCase(DefaultUserRole.ACCAD.getIdentifier()) == 0);
    }

    @After
    public void deleteTestUsers(){
        userDao.deleteUser(testSysUser);
        userDao.deleteUser(testAdminUser);
        userDao.deleteUser(testSimpleUser);
        userDao.deleteUser(testAccUser);
    }
}
