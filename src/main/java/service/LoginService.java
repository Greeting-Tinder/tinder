package service;

import dao.UserDAO;
import entity.User;

public class LoginService {
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
