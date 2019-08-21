package by.epam.pialetskialiaksei.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message extends Entity {
    private String text;
    private int conversationId;
    private int userId;
    private LocalDateTime creationTime;
    private boolean important;

    public Message() {
    }

    public Message(String text, int conversationId, int userId, LocalDateTime creationTime, boolean important) {
        this.text = text;
        this.conversationId = conversationId;
        this.userId = userId;
        this.creationTime = creationTime;
        this.important = important;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return getConversationId() == message.getConversationId() &&
                getUserId() == message.getUserId() &&
                isImportant() == message.isImportant() &&
                Objects.equals(getText(), message.getText()) &&
                Objects.equals(getCreationTime(), message.getCreationTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getConversationId(), getUserId(), getCreationTime(), isImportant());
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", conversationId=" + conversationId +
                ", userId=" + userId +
                ", creationTime=" + creationTime +
                ", important=" + important +
                "} " + super.toString();
    }
}