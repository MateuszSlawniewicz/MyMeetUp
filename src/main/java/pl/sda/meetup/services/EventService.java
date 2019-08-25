package pl.sda.meetup.services;

import pl.sda.meetup.dto.EventDto;

import java.util.List;

public interface EventService {
   EventDto saveEvent(EventDto eventDto);
   List<EventDto> showAllEvents();
    EventDto updateEvent(EventDto eventDto);
    Long deleteEvent(Long id);
}
