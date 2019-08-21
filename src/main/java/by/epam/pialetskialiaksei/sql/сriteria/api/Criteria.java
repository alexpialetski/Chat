package by.epam.pialetskialiaksei.sql.сriteria.api;

import by.epam.pialetskialiaksei.sql.builder.api.Builder;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface Criteria {
    DTO process(ResultSet resultSet) throws SQLException;
    void prepareStatement(PreparedStatement statement) throws SQLException;
    String getQuery();
    Builder createBuilder();
}
