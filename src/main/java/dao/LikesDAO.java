package dao;

import entity.Like;
import libs.DbConnection;
import libs.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikesDAO implements DAO<Like> {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);

    private List<Like> likes;

    public LikesDAO() {
        likes = new LinkedList<>();
        getAll();
    }

    @Override
    public void getAll() {
        likes = new LinkedList<>();
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "SELECT * FROM likes";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                likes.add(new Like(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_likes"),
                        resultSet.getInt("user_liked")));
            }
        } catch (SQLException e) {
            LOG.error("Can't connect to database " + e);
        }
    }

    @Override
    public List<Like> getDatabase() {
        return likes;
    }


    @Override
    public List<Integer> getAllId() {
        return likes.stream().map(Like::getId).collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public void add(Like like) {
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "INSERT INTO likes (user_likes, user_liked) values (?,?)";
            PreparedStatement insertLikes = conn.prepareStatement(SQL);
            insertLikes.setInt(1, like.getUser_likes());
            insertLikes.setInt(2, like.getUser_liked());
            insertLikes.executeUpdate();
            likes.add(like);
        } catch (SQLException e) {
            LOG.error("Can't connect to database " + e);
        }
    }

    @Override
    public Stream<Like> stream() {
        return likes.stream();
    }

    @Override
    public Iterator<Like> iterator() {
        return likes.iterator();
    }

    @Override
    public Like get(int id) {
        return likes.stream().filter(oneLike -> oneLike.getId() == id).collect(Collectors.toList()).get(0);
    }
}
