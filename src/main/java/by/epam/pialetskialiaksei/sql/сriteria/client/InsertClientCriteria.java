package by.epam.pialetskialiaksei.sql.сriteria.client;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.Role;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.InsertCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertClientCriteria extends ClientCriteria<Client> implements InsertCriteria {

    public InsertClientCriteria(Client sourceOfInfo) {
        super(sourceOfInfo);
        query = "INSERT INTO client(first_name, last_name, User_idUser) VALUES(?,?,?);";
    }
    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        Client client = (Client) createBuilder().build(resultSet);
        return new DTO<>(client);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        int i = 1;
        statement.setString(i++, sourceOfInfo.getFirstName());
        statement.setString(i++, sourceOfInfo.getLastName());
        statement.setInt(i++, sourceOfInfo.getUserId());
    }

    @Override
    public Builder createBuilder() {
        return new ClientBuilder();
    }
}
