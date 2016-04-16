package co.refiere.models;

import org.junit.Ignore;
import org.junit.Test;

import co.refiere.dao.RefiereUserDao;

public class RefiereUserTest {

    @Ignore
    @Test
    public void testCreateRefiereUser() {

        RefiereUser newUser = new RefiereUser();
        //TODO: Provide Random integer - modify RefiereUser.dbm.xml to assign
        //newUser.setId();
        newUser.setLogin("testDAO");
        newUser.setPassword("testpassDAO");

        RefiereUserDao userDao = new RefiereUserDao();
        userDao.save(newUser);
    }

}
