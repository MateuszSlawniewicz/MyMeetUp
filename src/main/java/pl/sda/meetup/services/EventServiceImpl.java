package pl.sda.meetup.services;

import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.repositories.EventRepository;
import pl.sda.meetup.dto.EventDto;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;


    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public EventDto saveEvent(EventDto eventDto) {
        return null;
    }

    @Override
    public List<EventDto> showAllEvents() {
        return null;
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        return null;
    }

    @Override
    public Long deleteEvent(Long id) {
        return null;
    }
}
