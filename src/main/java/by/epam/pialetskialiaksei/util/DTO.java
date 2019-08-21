package by.epam.pialetskialiaksei.util;

public class DTO<T> {
    private T data;

    public DTO(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "data=" + data +
                '}';
    }
}
