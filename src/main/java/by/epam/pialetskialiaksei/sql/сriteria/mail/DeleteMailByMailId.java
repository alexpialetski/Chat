package by.epam.pialetskialiaksei.sql.сriteria.mail;

import by.epam.pialetskialiaksei.entity.Mail;
import by.epam.pialetskialiaksei.sql.builder.MailBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.DeleteCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.mail.api.MailCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteMailByMailId extends MailCriteria<Mail> implements DeleteCriteria {

    public DeleteMailByMailId(Mail sourceOfInfo) {
        super(sourceOfInfo);
        query = "DELETE FROM university_admission.mail WHERE mailId=?;";
    }

    @Override
    public DTO process(ResultSet resultSet) {
        return null;
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
