package by.epam.pialetskialiaksei.sql.сriteria.mail;

import by.epam.pialetskialiaksei.entity.Mail;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.MailBuilder;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.mail.api.MailCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindMailByMailId extends MailCriteria<Mail> implements FindCriteria {

    public FindMailByMailId(Mail sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT * FROM university_admission.mail WHERE mailId=?;";
    }

    @Override
    public DTO process(ResultSet resultSet) {
        Mail mail = (Mail) createBuilder().build(resultSet);
        return new DTO<>(mail);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, sourceOfInfo.getMailId());
    }

    @Override
    public Builder createBuilder() {
        return new MailBuilder();
    }
}
