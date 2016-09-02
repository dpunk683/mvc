package by.pvt.academy.yarkovich.exceptions;

/**
 * Created by dima on 27.08.2016.
 */
public class DAOException extends Exception {

    private Exception exception;

    public DAOException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}