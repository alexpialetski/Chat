package by.epam.pialetskialiaksei.sql.сriteria.user.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class UserCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public UserCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
