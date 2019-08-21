package by.epam.pialetskialiaksei.entity;

import java.util.Objects;

public class Client extends Entity {
    private String firstName;
    private String lastName;
    private String about;
    private int userId;

    public Client(String firstName, String lastName, String about, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.userId= userId;
    }

    public Client(String firstName, String lastName, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId= userId;
    }

    public Client() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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
        Client client = (Client) o;
        return getUserId() == client.getUserId() &&
                Objects.equals(getFirstName(), client.getFirstName()) &&
                Objects.equals(getLastName(), client.getLastName()) &&
                Objects.equals(getAbout(), client.getAbout());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAbout(), getUserId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", about='" + about + '\'' +
                ", userId=" + userId +
                "} " + super.toString();
    }
}
