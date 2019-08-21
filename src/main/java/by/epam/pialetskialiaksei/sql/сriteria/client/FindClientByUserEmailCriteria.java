package by.epam.pialetskialiaksei.sql.сriteria.client;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindClientByUserEmailCriteria extends ClientCriteria<String> implements FindCriteria {

    public FindClientByUserEmailCriteria(String sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT client.id, client.first_name, client.last_name, client.about\n" +
                "FROM client\n" +
                "       INNER JOIN user u on client.User_idUser = u.id\n" +
                "WHERE u.email = ?;";
    }
    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        Client client = (Client) createBuilder().build(resultSet);
        return new DTO<>(client);
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
