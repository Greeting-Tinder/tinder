package service;

import dao.LikesDAO;
import dao.UserDAO;
import entity.Like;
import entity.User;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikeService {


    private UserDAO users;
    private LikesDAO likes;
    private List<Integer> userIds;
    private boolean liked;
    private int id;

    public int getLocalId() {
        return id;
    }

    public LikeService() {
        liked = false;
        likes = new LikesDAO();
        users = new UserDAO();
        userIds = users.getAllId();
    }

    public void like(int user_liked) {
        liked = true;
        Like like = new Like(id, user_liked);
        if (!likes.getDatabase().contains(like))
            likes.add(like);
    }

    public User getFirst() {
        return getUserNotMe().collect(Collectors.toList()).get(0);
    }

    public Stream<User> getUserNotMe() {
        return users.stream()
                .filter(oneUser -> oneUser.getId() != id);
    }

    public boolean isLast() {
        return userIds.isEmpty();
    }

    public boolean isLiked() {
        return liked;
    }


    public User getNext(int user_liked) throws RuntimeException {
        userIds.remove(Integer.valueOf(user_liked));
        if (!isLast())
            return users.get(userIds.get(0));
        else if (isLiked())
            throw new RuntimeException();
        else userIds = users.getAllId();
        return null;
    }


    public void setLocalId(int id) {
        this.id = id;
    }
}

