package pl.sda.meetup.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sda.meetup.exceptions.AuthenticationException;
import pl.sda.meetup.exceptions.EventException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ControllerForHandlingException {


    @ExceptionHandler(EventException.class)
    public String eventExceptionHandling(HttpServletRequest req, Exception ex, Model model) {
        return handleException(req, ex, model);
    }

    @ExceptionHandler(AuthenticationException.class)
    public String authExceptionHandling(HttpServletRequest req, Exception ex, Model model) {
        return handleException(req, ex, model);
    }


    private String handleException(HttpServletRequest req, Exception ex, Model model) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex.getMessage());
        model.addAttribute("exception", ex.getMessage());
        return "exceptionForm";
    }


}
