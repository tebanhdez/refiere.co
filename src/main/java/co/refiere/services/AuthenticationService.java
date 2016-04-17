package co.refiere.services;

import java.io.IOException;
import java.util.StringTokenizer;

import org.eclipse.jetty.util.StringUtil;

import com.google.common.io.BaseEncoding;

import co.refiere.dao.RefiereUserDao;
import co.refiere.models.RefiereUser;


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
        RefiereUserDao userDao = new RefiereUserDao();
        RefiereUser userObject = userDao.findByLogin(username);

        boolean authenticationStatus = false;

        if(userObject != null){
            System.out.println("User found!");
            return checkPassword(userObject.getPassword(), password);
        }
        return authenticationStatus;
    }

    private boolean checkPassword(String password, String password2) {
        return password.equals(password2);
    }}
