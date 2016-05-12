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
        System.out.println("------------CONTRASEÑA Y USER!!!!!!!!----------");
        final String username = tokenizer.nextToken();
        System.out.println(username);
        final String password = tokenizer.nextToken();
        System.out.println(password);

        if(StringUtil.isBlank(username) || StringUtil.isBlank(password))
            return false;
        SimpleUser userObject = findUserByLogin(username);
        System.out.println("SIGUIENTE OBJETO!!");
        System.out.println(userObject);

        boolean authenticationStatus = false;

        if(userObject != null){
            System.out.println("User found!");
            System.out.println("AUTHENTICATION STATUSSSSSSSSSSSSSSSS222222222222222");
            System.out.println(checkPassword(userObject.getPassword(), password));
            return checkPassword(userObject.getPassword(), password);
        }
        System.out.println("AUTHENTICATION STATUSSSSSSSSSSSSSSSS");
        System.out.println(authenticationStatus);
        return authenticationStatus;
    }

    public SimpleUser findUserByLogin(final String username) {
      System.out.println("AQUI IMPRIME EL USERRR DE FINDUSERBYLOGIN");
      System.out.println(username);
      RefiereUserDao userDao = new RefiereUserDao();
      SimpleUser userObject = userDao.findByLogin(username);
      System.out.println(userObject);
      return userObject;
    }

    private boolean checkPassword(String password, String password2) {
        return password.equals(password2);
    }}
