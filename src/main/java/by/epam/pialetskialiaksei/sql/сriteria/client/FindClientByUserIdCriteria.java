package by.epam.pialetskialiaksei.sql.сriteria.client;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindClientByUserIdCriteria extends ClientCriteria<Integer> implements FindCriteria {

    public FindClientByUserIdCriteria(Integer sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT c.id, c.first_name, c.last_name, c.about, c.User_idUser\n" +
                "FROM client c\n" +
                "WHERE c.User_idUser= ?;\n";
    }
    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        Client client = (Client) createBuilder().build(resultSet);
        return new DTO<>(client);
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
