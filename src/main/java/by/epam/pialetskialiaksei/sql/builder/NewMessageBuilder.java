package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.entity.NewMessage;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMessageBuilder extends Builder<NewMessage> {
    private final static Logger LOG = LogManager
            .getLogger(NewMessageBuilder.class);
    @Override
    public NewMessage build(ResultSet rs) {
        NewMessage newMessage = new NewMessage();
        try {
            newMessage.setId(rs.getInt(Fields.ENTITY_ID));
            newMessage.setConversationId(Integer.parseInt(rs.getString(Fields.CONVERSATION_FOREIGN_KEY_ID)));
            newMessage.setUserId(Integer.parseInt(rs.getString(Fields.USER_FOREIGN_KEY_ID)));
            newMessage.setMessageId(Integer.parseInt(rs.getString(Fields.MESSAGE_FOREIGN_KEY_ID)));
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to newMessage", e);
        }
        return newMessage;
    }
}
