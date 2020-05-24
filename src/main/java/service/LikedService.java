package service;

import dao.LikesDAO;
import dao.UserDAO;
import entity.Like;
import entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class LikedService {
    private LikesDAO likesDao;
    private UserDAO usersDao;

    public LikedService() {
        usersDao = new UserDAO();
        likesDao = new LikesDAO();
    }

    public List<User> getLikedUsers(int id) {
        List<Like> likedUserIds = getLikedUserIds(id);
        return usersDao.stream()
                .filter(oneUser -> likedUserIds.contains(new Like(id, oneUser.getId())))
                .collect(Collectors.toList());
    }

    public List<Like> getLikedUserIds(int localId) {
        likesDao.read();
        return likesDao.stream()
                .filter(oneLike -> oneLike.getUser_likes() == localId)
                .collect(Collectors.toList());
    }

}
