package by.epam.pialetskialiaksei.sql.сriteria.conversationgroup;

import by.epam.pialetskialiaksei.entity.Conversation;
import by.epam.pialetskialiaksei.entity.ConversationGroup;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.ConversationGroupBuilder;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.conversationgroup.api.ConversationGroupCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindConversationByIdCriteria extends ConversationGroupCriteria<ConversationGroup> {

    public FindConversationByIdCriteria(ConversationGroup sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT * FROM user WHERE email=?";
    }

    @Override
    public DTO process(ResultSet resultSet) {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.build(resultSet);
        return new DTO<>(user);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {

    }

    @Override
    public Builder createBuilder() {
        return new ConversationGroupBuilder();
    }
}
