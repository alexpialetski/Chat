package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder extends Builder<Client> {
    private final static Logger LOG = LogManager
            .getLogger(ClientBuilder.class);
    @Override
    public Client build(ResultSet rs) {
        Client client = new Client();
        try {
            client.setId(rs.getInt(Fields.ENTITY_ID));
            client.setAbout(rs.getString(Fields.CLIENT_ABOUT));
            client.setFirstName(rs.getString(Fields.CLIENT_FIRST_NAME));
            client.setLastName(rs.getString(Fields.CLIENT_LAST_NAME));
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to client", e);
        }
        return client;
    }
}
