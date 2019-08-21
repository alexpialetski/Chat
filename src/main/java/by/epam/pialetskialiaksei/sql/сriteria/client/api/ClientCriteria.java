package by.epam.pialetskialiaksei.sql.сriteria.client.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class ClientCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public ClientCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
