package by.epam.pialetskialiaksei.sql.сriteria.user;

import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.sql.builder.UserBuilder;
import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserByIdCriteria extends UserCriteria<Integer> {

    public FindUserByIdCriteria(Integer sourceOfInfo) {
        super(sourceOfInfo);
        query = "SELECT user.id,\n" +
                "       user.email,\n" +
                "       user.password,\n" +
                "       s.statusRu,\n" +
                "       s.statusEng,\n" +
                "       r.role_name\n" +
                "FROM user\n" +
                "       INNER JOIN status s ON user.status_id = s.id\n" +
                "       INNER JOIN role r ON user.role_id = r.id\n" +
                "WHERE user.id = ?\n";
    }

    @Override
    public DTO process(ResultSet resultSet) {
        User user = (User) createBuilder().build(resultSet);
        return new DTO<>(user);
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setInt(1, sourceOfInfo);
    }

    @Override
    public Builder createBuilder() {
        return new UserBuilder();
    }
}
