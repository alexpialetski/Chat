package by.epam.pialetskialiaksei.sql.сriteria.conversation.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class ConversationCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public ConversationCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
