package co.refiere.models;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.dao.RefiereUserDao;

public class RefiereUserTest {

    String LOGIN = UUID.randomUUID().toString().substring(0, 18);

    @Test
    public void testCreateRefiereUser() {

        RefiereUser newUser = new RefiereUser();
        //TODO: Provide Random integer - modify RefiereUser.dbm.xml to assign
        //newUser.setId();
        newUser.setLogin(LOGIN);
        newUser.setPassword("password");

        RefiereUserDao userDao = new RefiereUserDao();
        System.out.println(String.format(">>> Saving User: %s, pass: %s", LOGIN, "password"));
        userDao.save(newUser);

        RefiereUser user = userDao.findByLogin(LOGIN);
        Assert.assertNotNull("User not found",user);
        Assert.assertTrue(user.getLogin().equals(LOGIN));
        Assert.assertTrue(user.getPassword().equals("password"));
    }

}
