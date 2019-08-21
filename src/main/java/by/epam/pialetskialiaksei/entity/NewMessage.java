package by.epam.pialetskialiaksei.entity;

import java.util.Objects;

public class NewMessage extends Entity {

    private int conversationId;
    private int userId;
    private  int messageId;

    public NewMessage() {
    }

    public NewMessage(int conversationId, int userId, int messageId) {
        this.conversationId = conversationId;
        this.userId = userId;
        this.messageId = messageId;
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

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewMessage that = (NewMessage) o;
        return getConversationId() == that.getConversationId() &&
                getUserId() == that.getUserId() &&
                getMessageId() == that.getMessageId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConversationId(), getUserId(), getMessageId());
    }

    @Override
    public String toString() {
        return "NewMessage{" +
                "conversationId=" + conversationId +
                ", userId=" + userId +
                ", messageId=" + messageId +
                "} " + super.toString();
    }
}