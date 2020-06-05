package service;

import dao.LikesDAO;
import dao.UserDAO;
import entity.User;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);
    private UserDAO users;
    private static boolean isLogged;
    public LoginService() {
        isLogged = false;
        users = new UserDAO();
    }

    @SneakyThrows
    public int check(User user){
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

    public static void setLogged(boolean logged) {
        LoginService.isLogged = logged;
    }
}
