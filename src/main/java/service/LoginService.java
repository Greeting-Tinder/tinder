package service;

import dao.LikesDAO;
import dao.UserDAO;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);
    private UserDAO users;
    private boolean isLogged;
    public LoginService() {
        isLogged = false;
        users = new UserDAO();
    }

    public int check(User user) throws Exception {
            for (User us : users) {
                if (us.checkEqual(user)) {
                    isLogged = true;
                    return us.getId();
                }

            }

            throw new Exception("Login failed");
    }

    public boolean isLogged() {
        return isLogged;
    }
}
