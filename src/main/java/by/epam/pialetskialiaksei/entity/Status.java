package by.epam.pialetskialiaksei.entity;

import java.util.Objects;

public class Status extends Entity{
    private String statusRu;
    private String statusEng;

    public Status() {
    }

    public Status(String statusRu, String statusEng) {
        this.statusRu = statusRu;
        this.statusEng = statusEng;
    }

    public String getStatusRu() {
        return statusRu;
    }

    public void setStatusRu(String statusRu) {
        this.statusRu = statusRu;
    }

    public String getStatusEng() {
        return statusEng;
    }

    public void setStatusEng(String statusEng) {
        this.statusEng = statusEng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals(getStatusRu(), status.getStatusRu()) &&
                Objects.equals(getStatusEng(), status.getStatusEng());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusRu(), getStatusEng());
    }
}
