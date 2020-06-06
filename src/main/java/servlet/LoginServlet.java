package servlet;

import dao.LikesDAO;
import entity.User;
import libs.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LoginService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);
    private LoginService loginService;

    public LoginServlet() {
        loginService = new LoginService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (!loginService.isLogged()) {
            TemplateEngine engine = new TemplateEngine("src/main/resources/templates");
            engine.render("login.ftl", resp);
        } else {
            resp.sendRedirect("/like/");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            int id = loginService.check(new User(login, password));
            Cookie c = new Cookie("%ID%", String.valueOf(id));
            c.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(c);
            resp.sendRedirect("/like/");
        } catch (Exception ex) {
            LOG.warn("Login error " + ex);
            LOG.info("Redirecting to login");
            resp.sendRedirect("/login");
        }
    }


}
