package by.epam.pialetskialiaksei.sql.сriteria.friend.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;
import by.epam.pialetskialiaksei.util.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FriendCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public FriendCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
