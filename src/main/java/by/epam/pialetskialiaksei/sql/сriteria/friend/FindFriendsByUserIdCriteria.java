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

public class FindFriendsByUserIdCriteria extends FriendCriteria<Integer> implements FindCriteria {
    public FindFriendsByUserIdCriteria(Integer sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT c.id, c.first_name, c.last_name, c.about, c.User_idUser\n" +
                "FROM friend\n" +
                "       INNER JOIN client c ON friend.User_idUser_friend = c.User_idUser\n" +
                "WHERE friend.User_idUser= ?;";
    }

    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        List<Client> clients = new ArrayList<>();
        do {
            clients.add((Client) createBuilder().build(resultSet));
        } while (resultSet.next());
        return new DTO<>(clients);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setInt(1, sourceOfInfo);
    }

    @Override
    public Builder createBuilder() {
        return new ClientBuilder();
    }
}
