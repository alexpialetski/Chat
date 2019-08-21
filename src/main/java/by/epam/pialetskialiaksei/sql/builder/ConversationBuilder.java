package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.Conversation;
import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationBuilder extends Builder<Conversation> {
    private final static Logger LOG = LogManager
            .getLogger(ConversationBuilder.class);

    @Override
    public Conversation build(ResultSet rs) {
        Conversation conversation = new Conversation();
        try {
            conversation.setId(rs.getInt(Fields.ENTITY_ID));
            conversation.setName(rs.getString(Fields.CONVERSATION_NAME));
            conversation.setDateCreation(
                    rs.getDate(Fields.CONVERSATION_DATE_CREATION).toLocalDate());
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to conversation", e);
        }
        return conversation;
    }
}
