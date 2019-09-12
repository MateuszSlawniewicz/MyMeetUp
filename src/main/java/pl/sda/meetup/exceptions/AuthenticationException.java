package pl.sda.meetup.exceptions;

public class AuthenticationException extends RuntimeException {


    public AuthenticationException(String message) {
        super(message);
    }
}
