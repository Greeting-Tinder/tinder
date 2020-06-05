package servlet;

import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String logout = req.getParameter("logout");
        if (logout != null) {

            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie oneCookie : cookies) {
                    if (oneCookie.getName().equals("%ID%")) {
                        oneCookie.setMaxAge(0);
                        oneCookie.setValue(null);
                        oneCookie.setPath("/");
                        resp.addCookie(oneCookie);
                    }
                }
            }
            resp.sendRedirect("/login");
        }
    }

}
