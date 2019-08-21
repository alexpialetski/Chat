package by.epam.pialetskialiaksei.exception;

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "DaoException: " + super.getMessage();
    }
}
