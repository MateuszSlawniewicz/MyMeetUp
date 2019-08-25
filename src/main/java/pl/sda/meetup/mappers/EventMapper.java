package pl.sda.meetup.mappers;

import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dto.EventDto;

@Component
public class EventMapper {

    public Event fromEventDtoToEvent(EventDto eventDto) {
        return null;
    }

    public EventDto fromEventToEventDto(Event event) {
        return null;
    }

}
