package by.epam.pialetskialiaksei.sql.сriteria.friend;

import by.epam.pialetskialiaksei.entity.Role;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.InsertCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.api.FriendCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertFriendsCriteria extends FriendCriteria<Integer> implements InsertCriteria {

    private final Integer friendId;
    public InsertFriendsCriteria(Integer userId, Integer friendId) {
        super(userId);
        this.friendId = friendId;
        query = "INSERT INTO friend(friend.user_idUser, friend.user_idUser_friend) VALUES (?,?);";
    }

    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setInt(1, sourceOfInfo);
        statement.setInt(2, friendId);
    }

    @Override
    public Builder createBuilder() {
        return new UserBuilder();
    }
}
