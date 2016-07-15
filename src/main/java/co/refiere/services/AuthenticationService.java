package co.refiere.services;

import java.io.IOException;
import java.util.StringTokenizer;

import org.eclipse.jetty.util.StringUtil;

import com.google.common.io.BaseEncoding;

import co.refiere.dao.RefiereUserDao;
import co.refiere.models.SimpleUser;


public class AuthenticationService {
    public boolean authenticate(String authCredentials) {

        if (null == authCredentials)
            return false;

        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {

            byte[] decodedBytes = BaseEncoding.base64().decode(encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        if(StringUtil.isBlank(username) || StringUtil.isBlank(password))
            return false;
        SimpleUser userObject = findUserByLogin(username);

        boolean authenticationStatus = false;

        if(userObject != null){
            return checkPassword(userObject.getPassword(), password);
        }
        return authenticationStatus;
    }

    public SimpleUser findUserByLogin(final String username) {
      RefiereUserDao userDao = new RefiereUserDao();
      SimpleUser userObject = userDao.findByLogin(username);
      return userObject;
    }

    private boolean checkPassword(String password, String password2) {
        return password.equals(password2);
    }}
