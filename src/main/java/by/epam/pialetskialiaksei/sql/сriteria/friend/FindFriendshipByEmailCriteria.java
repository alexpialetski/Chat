package by.epam.pialetskialiaksei.sql.сriteria.friend;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.api.FriendCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindFriendshipByEmailCriteria extends FriendCriteria<String> implements FindCriteria {
    private final Integer userId;
    public FindFriendshipByEmailCriteria(String email, Integer userId) {
        super(email);
        this.userId = userId;
        query = "SELECT friend.id\n" +
                "FROM friend\n" +
                "       INNER JOIN user u on friend.User_idUser_friend = u.id\n" +
                "WHERE u.email = ?\n" +
                "  AND friend.User_idUser = ?;";
    }

    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        return new DTO<>(new Client());
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, sourceOfInfo);
        statement.setInt(2, userId);
    }

    @Override
    public Builder createBuilder() {
        return new ClientBuilder();
    }
}
