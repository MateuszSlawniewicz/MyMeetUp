package pl.sda.meetup.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class EventContextHolder {
    private Long eventId;
}
