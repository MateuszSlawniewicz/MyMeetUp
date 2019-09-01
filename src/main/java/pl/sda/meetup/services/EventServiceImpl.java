package pl.sda.meetup.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.EventRepository;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.mappers.EventMapper;
import pl.sda.meetup.mappers.UserMapper;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, UserMapper userMapper, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public EventDto saveEvent(EventDto eventDto, String user) {
        User userFromDB = userRepository.findByEmail(user);
        Event event = eventMapper.fromEventDtoToEvent(eventDto);
        event.setUser(userFromDB);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.fromEventToEventDto(savedEvent);
    }

    @Override
    public List<EventDto> showAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::fromEventToEventDto)
                .sorted(Comparator.comparing(EventDto::getStart))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> showAllCurrentEvents() {
        return showAllEvents().stream()
                .filter(eventDto -> eventDto.getStart().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }


    @Override
    public EventDto updateEvent(EventDto eventDto) {
        return null;
    }

    @Override
    public Long deleteEvent(Long id) {
        return null;
    }

    @Override
    public List<EventDto> showAllEventsIgnoreCase(String title) {
        if (title == null) {
            return showAllEvents();
        }
        return eventRepository.findAllByTitleIgnoreCase(title).stream()
                .map(eventMapper::fromEventToEventDto)
                .collect(Collectors.toList());
    }
}
