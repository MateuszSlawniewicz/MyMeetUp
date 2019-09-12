package pl.sda.meetup.services;

import pl.sda.meetup.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto saveEvent(EventDto eventDto);

    List<EventDto> showAllEvents();

    List<EventDto> showAllCurrentEvents();

    List<EventDto> showAllEventsIgnoreCase(String title);

    List<EventDto> findEventsWithSpecialConditions(String title, String type);

    EventDto getEventById(Long id);

    Boolean checkIfUserParticipant(Long eventId);

    EventDto saveParticipation(Long eventId);

    EventDto deleteParticipation(Long eventId);
}
