package service;

import dao.UserDAO;
import entity.User;

public class RegisterService {

    private UserDAO userDAO;

    public  RegisterService(){
        userDAO=new UserDAO();
    }

    public void register(User user) {
        userDAO.add(user);
    }



}
