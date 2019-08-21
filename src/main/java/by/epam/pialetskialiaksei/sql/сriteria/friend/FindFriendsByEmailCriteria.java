package by.epam.pialetskialiaksei.sql.сriteria.friend;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.User;
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

public class FindFriendsByEmailCriteria extends FriendCriteria<String> implements FindCriteria {
    public FindFriendsByEmailCriteria(String sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT c.id, c.first_name, c.last_name, c.about, c.User_idUser\n" +
                "FROM friend\n" +
                "       INNER JOIN client c ON friend.User_idUser_friend = c.User_idUser\n" +
                "       INNER JOIN user u on friend.User_idUser = u.id\n" +
                "WHERE u.email = ?;\n";
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
        statement.setString(1, sourceOfInfo);
    }

    @Override
    public Builder createBuilder() {
        return new ClientBuilder();
    }
}
