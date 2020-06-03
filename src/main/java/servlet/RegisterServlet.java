package servlet;


import entity.User;
import service.RegisterService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

    public class RegisterServlet extends HttpServlet {

        private RegisterService registerService;

        public RegisterServlet() {
            registerService = new RegisterService();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            Path path = Paths.get("src/main/resources/templates/register.html");
            ServletOutputStream outputStream = resp.getOutputStream();
            Files.copy(path, outputStream);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            registerService.register(new User(
                    req.getParameter("email"),
                    req.getParameter("password"),
                    req.getParameter("username"),
                    req.getParameter("job")));
            resp.sendRedirect("/login/");
        }
    }


