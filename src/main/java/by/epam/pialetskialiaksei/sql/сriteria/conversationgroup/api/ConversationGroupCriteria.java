package by.epam.pialetskialiaksei.sql.сriteria.conversationgroup.api;

import by.epam.pialetskialiaksei.sql.сriteria.api.Criteria;

public abstract class ConversationGroupCriteria<T> implements Criteria {
    protected String query;
    protected T sourceOfInfo;

    public ConversationGroupCriteria(T sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getQuery() {
        return query;
    }
}
