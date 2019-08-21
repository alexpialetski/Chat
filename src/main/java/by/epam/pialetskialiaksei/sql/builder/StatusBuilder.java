package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.entity.Status;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusBuilder extends Builder<Status> {
    private final static Logger LOG = LogManager
            .getLogger(StatusBuilder.class);
    @Override
    public Status build(ResultSet rs) {
        Status status = new Status();
        try {
            status.setId(rs.getInt(Fields.ENTITY_ID));
            status.setStatusRu(rs.getString(Fields.USER_STATUS_RU));
            status.setStatusEng(rs.getString(Fields.USER_STATUS_ENG));
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to status", e);
        }
        return status;
    }
}
