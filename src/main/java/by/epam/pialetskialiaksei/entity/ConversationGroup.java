package by.epam.pialetskialiaksei.entity;

import java.util.Objects;

public class ConversationGroup extends Entity {
    private int conversationId;
    private int userId;

    public ConversationGroup(int conversationId, int userId) {
        this.conversationId = conversationId;
        this.userId = userId;
    }

    public ConversationGroup() {
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConversationGroup that = (ConversationGroup) o;
        return getConversationId() == that.getConversationId() &&
                getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConversationId(), getUserId());
    }

    @Override
    public String toString() {
        return "ConversationGroup{" +
                "conversationId=" + conversationId +
                ", userId=" + userId +
                "} " + super.toString();
    }
}