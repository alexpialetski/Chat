package by.epam.pialetskialiaksei.sql.сriteria.message;

import by.epam.pialetskialiaksei.entity.Message;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.MessageBuilder;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.message.api.MessageCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindMessageByIdCriteria extends MessageCriteria<Message> {

    public FindMessageByIdCriteria(Message sourceOfInfo) {
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
        return new MessageBuilder();
    }
}
