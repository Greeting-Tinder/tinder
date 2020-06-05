package dao;

import entity.User;
import libs.DbConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDAO implements DAO<User> {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);
    private List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
        getAll();
    }

    @Override
    public void getAll() {
        users = new LinkedList<>();
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("email"),
                        resultSet.getString("password"), resultSet.getString("username"),
                        resultSet.getString("job"), resultSet.getString("imgurl")));
            }
        } catch (SQLException e) {
            LOG.error("Can't connect to database " + e);
        }
    }

    @Override
    public List<User> getDatabase() {
        return users;
    }


    @Override
    public List<Integer> getAllId() {
        getAll();
        List<Integer> result = new LinkedList<>();
        users.forEach(user -> result.add(user.getId()));
        return result;
    }


    @Override
    public void add(User user) {
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "INSERT INTO users (email, password, username, job, imgurl) values (?,?,?,?,?)";
            PreparedStatement insertUser = conn.prepareStatement(SQL);
            insertUser.setString(1, user.getEmail());
            insertUser.setString(2, user.getPassword());
            insertUser.setString(3, user.getUsername());
            insertUser.setString(4, user.getJob());
            insertUser.setString(5, "https://robohash.org/24.218.243.26.png");

            insertUser.executeUpdate();
            users = new ArrayList<>();
            getAll();


        } catch (SQLException e) {
            LOG.error("Can't connect to database " + e);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public User get(int id) {
        return users.stream().filter(oneUser -> oneUser.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Stream<User> stream() {
        return users.stream();
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
