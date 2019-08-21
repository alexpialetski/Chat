package by.epam.pialetskialiaksei.sql.сriteria.message.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class MessageCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public MessageCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
