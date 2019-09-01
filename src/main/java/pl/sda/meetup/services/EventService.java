package pl.sda.meetup.services;

import pl.sda.meetup.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto saveEvent(EventDto eventDto, String user);

    List<EventDto> showAllEvents();

    List<EventDto> showAllCurrentEvents();

    EventDto updateEvent(EventDto eventDto);

    Long deleteEvent(Long id);

    List<EventDto> showAllEventsIgnoreCase(String title);
}
