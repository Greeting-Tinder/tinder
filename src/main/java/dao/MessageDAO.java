package dao;

import entity.Message;
import libs.DbConnection;

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

public class MessageDAO implements DAO<Message> {
    private List<Message> messages;

    public MessageDAO() {
        read();
    }

    @Override
    public void read() {
        messages = new ArrayList<>();
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "SELECT * FROM message";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_to"),
                        resultSet.getInt("user_from"),
                        resultSet.getString("message"),
                        resultSet.getInt("localId"),
                        resultSet.getString("dateTime")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getDatabase() {
        return messages;
    }

    @Override
    public List<Integer> getAllId() {
        return messages.stream().map(Message::getId)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Message get(int id) {
        return messages.stream()
                .filter(oneMessage -> oneMessage.getId() == id)
                .collect(Collectors.toList()).get(0);
    }


    @Override
    public void add(Message message) {
        try {
            Connection conn = DbConnection.getConnection();
            final String SQL = "INSERT INTO message (user_from, user_to, message, localId, datetime) values (?,?,?,?,?)";
            PreparedStatement insertMessage = conn.prepareStatement(SQL);
            insertMessage.setInt(1, message.getUserFrom());
            insertMessage.setInt(2, message.getUserTo());
            insertMessage.setString(3, message.getMessage());
            insertMessage.setInt(4, message.getLocalId());
            insertMessage.setString(5, message.getDateTime());

            insertMessage.executeUpdate();
            messages.add(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Message> stream() {
        return messages.stream();
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }


}
