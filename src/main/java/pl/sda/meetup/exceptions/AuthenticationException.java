package pl.sda.meetup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "user permission denied")
public class AuthenticationException extends RuntimeException {


    public AuthenticationException(String message) {
        super(message);
    }
}
