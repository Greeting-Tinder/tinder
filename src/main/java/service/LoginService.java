package service;

import dao.UserDAO;
import entity.User;
import lombok.SneakyThrows;

public class LoginService {

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
