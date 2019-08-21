package by.epam.pialetskialiaksei.sql.сriteria.client;

import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindAllClientsCriteria extends ClientCriteria<Client> implements FindCriteria {

    public FindAllClientsCriteria(Client sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT client.id, client.first_name, client.last_name, client.about, client.User_idUser\n" +
                "FROM client;";
    }

    public FindAllClientsCriteria() {
        super(null);
        query = "SELECT client.id, client.first_name, client.last_name, client.about, client.User_idUser\n" +
                "FROM client;";
    }

    @Override
    public DTO process(ResultSet resultSet) throws SQLException {
        List<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            clients.add((Client) createBuilder().build(resultSet));
        }
        return new DTO<>(clients);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
//        statement.setString(1, sourceOfInfo.getEmail());
    }

    @Override
    public Builder createBuilder() {
        return new ClientBuilder();
    }
}
