package servlet;

import entity.User;
import libs.TemplateEngine;
import service.MessageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MessagesServlet extends HttpServlet {
    private int senderId;
    private int receiverId;
    private MessageService service;

    public MessagesServlet() {
        service = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                senderId = Integer.parseInt(oneCookie.getValue());
        receiverId = Integer.parseInt(req.getPathInfo().substring(1));

        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");
        User user = service.getUser(receiverId);
        List<String> formattedMessages = service.getFormattedMessages(senderId, receiverId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("userTo", user.getUsername());
        if (!formattedMessages.isEmpty())
            data.put("messages", formattedMessages);
        else
            data.put("messages", new LinkedList<Integer>());
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        service.write(senderId, receiverId, message);
        resp.sendRedirect(String.format("/messages/%d", receiverId));
    }
}
