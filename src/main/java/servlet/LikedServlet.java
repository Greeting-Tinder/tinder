package servlet;

import entity.User;
import libs.TemplateEngine;
import service.LikedService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private int localId;
    private LikedService service;

    public LikedServlet() {
        service = new LikedService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies) {
            if (oneCookie.getName().equals("%ID%"))
                localId = Integer.parseInt(oneCookie.getValue());

        }

        List<User> likedUsers = service.getLikedUsers(localId);
        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeoples", likedUsers);
        engine.render("people-list.ftl", data, resp);
    }
}
