package servlet;

import dao.LikesDAO;
import entity.User;
import libs.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LikeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);
    private LikeService service;
    private User user;

    public LikeServlet() {
        service = new LikeService();
        user = service.getFirst();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                service.setLocalId(Integer.parseInt(oneCookie.getValue()));
        if (user.getId() == service.getLocalId())
            user = service.getNext(user.getId());


        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("name", user.getUsername());
        data.put("job", user.getJob());
        data.put("imgurl", user.getImgurl());
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String like = req.getParameter("like");
        if (like != null)
            service.like(Integer.parseInt(like));
        try {
            user = service.getNext(user.getId());
            resp.sendRedirect("/like/");
        } catch (RuntimeException ex) {
            LOG.info("Redirected to liked");
            resp.sendRedirect("/liked/");
        }
    }
}
