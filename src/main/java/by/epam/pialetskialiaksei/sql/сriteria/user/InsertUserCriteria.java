package by.epam.pialetskialiaksei.sql.сriteria.user;

import by.epam.pialetskialiaksei.entity.Role;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.api.InsertCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertUserCriteria extends UserCriteria<User> implements InsertCriteria {

    public InsertUserCriteria(User sourceOfInfo) {
        super(sourceOfInfo);
        query = "INSERT INTO user(email, password, role_id) VALUES(?,?,?);";
    }

    @Override
    public DTO process(ResultSet resultSet) {
        return null;
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        int i = 1;
        statement.setString(i++, sourceOfInfo.getEmail());
        statement.setString(i++, sourceOfInfo.getPassword());
        statement.setInt(i++, Role.valueOf(sourceOfInfo.getRole().toUpperCase()).getVal());
    }

    @Override
    public Builder createBuilder() {
        return new UserBuilder();
    }
}
