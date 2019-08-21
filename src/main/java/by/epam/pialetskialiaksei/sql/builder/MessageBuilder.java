package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageBuilder extends Builder<Message> {
    private final static Logger LOG = LogManager
            .getLogger(MessageBuilder.class);
    @Override
    public Message build(ResultSet rs) {
        Message message = new Message();
        try {
            message.setId(rs.getInt(Fields.ENTITY_ID));
            message.setText(rs.getString(Fields.MESSAGE_TEXT));
            message.setConversationId(Integer.parseInt(rs.getString(Fields.CONVERSATION_FOREIGN_KEY_ID)));
            message.setUserId(Integer.parseInt(rs.getString(Fields.USER_FOREIGN_KEY_ID)));
            message.setCreationTime(
                    rs.getTimestamp(Fields.MESSAGE_CREATION_TIME).toLocalDateTime());
            message.setImportant(rs.getBoolean(Fields.MESSAGE_IMPORTANT));
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to message", e);
        }
        return message;
    }
}
