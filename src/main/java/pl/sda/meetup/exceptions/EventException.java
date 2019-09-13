package pl.sda.meetup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "event not found")
public class EventException extends  RuntimeException {
    public EventException(String message) {
        super(message);
    }
}
