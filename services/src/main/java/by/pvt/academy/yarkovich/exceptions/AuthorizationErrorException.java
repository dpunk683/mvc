package by.pvt.academy.yarkovich.exceptions;

/**
 * Created by dima on 03.09.2016.
 */
public class AuthorizationErrorException extends Exception {
    String message;

    public AuthorizationErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
