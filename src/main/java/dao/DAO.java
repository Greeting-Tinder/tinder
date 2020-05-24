package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public interface DAO<T> extends Iterable<T> {

    void read() throws SQLException;

    List<T> getDatabase();

    List<Integer> getAllId();

    void add(T data);

    T get(int id);

    Stream<T> stream();
}
