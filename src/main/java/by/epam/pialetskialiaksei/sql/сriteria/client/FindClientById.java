package by.epam.pialetskialiaksei.sql.сriteria.client;

import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindClientById extends ClientCriteria<Integer> implements FindCriteria {

    public FindClientById(Integer sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT client.id, client.first_name, client.last_name, client.about, client.User_idUser\n" +
                "FROM client\n" +
                "WHERE client.id = ?;";
    }

    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        return new DTO<>(createBuilder().build(resultSet));
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
