package by.epam.pialetskialiaksei.sql.builder;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.entity.ConversationGroup;
import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationGroupBuilder extends Builder<ConversationGroup> {
    private final static Logger LOG = LogManager
            .getLogger(ConversationGroupBuilder.class);
    @Override
    public ConversationGroup build(ResultSet rs) {
        ConversationGroup ConversationGroup = new ConversationGroup();
        try {
            ConversationGroup.setId(rs.getInt(Fields.ENTITY_ID));
            ConversationGroup.setConversationId(rs.getInt(Fields.CONVERSATION_FOREIGN_KEY_ID));
            ConversationGroup.setUserId(Integer.parseInt(rs.getString(Fields.USER_FOREIGN_KEY_ID)));
        } catch (SQLException e) {
            LOG.error("Can not unmarshal ResultSet to ConversationGroup", e);
        }
        return ConversationGroup;
    }
}
