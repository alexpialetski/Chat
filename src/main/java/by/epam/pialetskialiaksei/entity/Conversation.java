package by.epam.pialetskialiaksei.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Conversation extends Entity {
    private String name;
    private LocalDate dateCreation;

    public Conversation() {
    }

    public Conversation(String name, LocalDate dateCreation) {
        this.name = name;
        this.dateCreation = dateCreation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Conversation that = (Conversation) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDateCreation(), that.getDateCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateCreation());
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "name='" + name + '\'' +
                ", dateCreation=" + dateCreation +
                "} " + super.toString();
    }
}
