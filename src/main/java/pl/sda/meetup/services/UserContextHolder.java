package pl.sda.meetup.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.meetup.exceptions.AuthenticationException;

@Service
public class UserContextHolder {

    String getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationException("User is anonymous");
        }
        return authentication.getName();
    }


}
