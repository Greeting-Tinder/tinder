package servlet;

import entity.User;
import service.LoginService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    private LoginService loginService;

    public LoginServlet() {
        loginService = new LoginService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!loginService.isLogged()) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie oneCookie : cookies) {
                    if (oneCookie.getName().equals("%ID%")) {
                        oneCookie.setMaxAge(0);
                        resp.addCookie(oneCookie);
                    }
                }
            }

            Path path = Paths.get("src/main/resources/templates/login.html");
            ServletOutputStream outputStream = resp.getOutputStream();
            Files.copy(path, outputStream);
        } else
            resp.sendRedirect("/like/");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            int id = loginService.check(new User(login, password));
            resp.addCookie(new Cookie("%ID%", String.valueOf(id)));
            resp.sendRedirect("/like/*");
        } catch (Exception ex) {
            resp.sendRedirect("/login/*");
        }
    }


}
