package org.arzimanoff.http.dao;

import lombok.SneakyThrows;
import org.arzimanoff.http.entity.Gender;
import org.arzimanoff.http.entity.Role;
import org.arzimanoff.http.entity.User;
import org.arzimanoff.http.util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SQL_SAVE_NEW_USER = """
            INSERT INTO users(name, birthday, email, password, role, gender)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private static final String SQL_GET_USER_BY_LOGIN_AND_PASSWORD = """
            SELECT * FROM users
            WHERE email = ? AND password = ?
            """;


    private UserDao() {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD);

        preparedStatement.setObject(1, email);
        preparedStatement.setObject(2, password);
        var resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = buildEntity(resultSet);
        }


        return Optional.ofNullable(user);
    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_SAVE_NEW_USER, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }
}
