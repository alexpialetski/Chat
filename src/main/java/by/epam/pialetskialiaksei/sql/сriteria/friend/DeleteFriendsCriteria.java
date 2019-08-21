package by.epam.pialetskialiaksei.sql.сriteria.friend;

import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.DeleteCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.InsertCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.api.FriendCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFriendsCriteria extends FriendCriteria<Integer> implements DeleteCriteria {

    private final Integer friendId;
    public DeleteFriendsCriteria(Integer userId, Integer friendId) {
        super(userId);
        this.friendId = friendId;
        query = "DELETE\n" +
                "FROM friend\n" +
                "WHERE User_idUser = ?\n" +
                "  AND User_idUser_friend = ?;";
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
