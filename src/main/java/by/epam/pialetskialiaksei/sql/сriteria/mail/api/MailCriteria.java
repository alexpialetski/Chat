package by.epam.pialetskialiaksei.sql.сriteria.mail.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class MailCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public MailCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
