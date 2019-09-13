package pl.sda.meetup.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.EventRepository;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.exceptions.EventException;
import pl.sda.meetup.exceptions.UserException;
import pl.sda.meetup.mappers.EventMapper;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserContextHolder userContextHolder;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, UserRepository userRepository, UserContextHolder userContextHolder) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userContextHolder = userContextHolder;
        this.userRepository = userRepository;
    }


    @Override
    public EventDto saveEvent(EventDto eventDto) {
        User userFromDB = userRepository.findByEmail(userContextHolder.getLoggedUserName())
                .orElseThrow(() -> new UserException("user not found"));
        Event event = eventMapper.fromEventDtoToEvent(eventDto);
        event.setUser(userFromDB);
        Set<User> participants = new HashSet<>();
        participants.add(userFromDB);
        event.setParticipants(participants);
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
    public List<EventDto> showAllEventsIgnoreCase(String title) {
        if (title == null) {
            return showAllEvents();
        }
        return eventRepository.findAllByTitleIgnoreCase(title).stream()
                .map(eventMapper::fromEventToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findEventsWithSpecialConditions(String title, String type) {

        List<EventDto> eventDtos = showAllEvents();

        switch (type) {
            case "future":
                eventDtos = eventDtos.stream()
                        .filter(e -> e.getStart().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList());
                break;
            case "current_and_future":
                eventDtos = eventDtos.stream()
                        .filter(e -> e.getEnd().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList());
                break;
            default:
                break;
        }
        if (eventDtos.isEmpty()) {
            throw new EventException("Event not found");
            //prepare handler
        }
        return eventDtos.stream()
                .filter(e -> e.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

    }

    @Override
    public EventDto getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::fromEventToEventDto)
                .orElseThrow(() -> new EventException("event not found"));
        //todo handler
    }

    @Override
    public Boolean checkIfUserParticipant(Long eventId) {
        String loggedUserName = userContextHolder.getLoggedUserName();
        return eventRepository.findById(eventId)
                .map(Event::getParticipants)
                .map(e -> e.contains(userRepository.findByEmail(loggedUserName).orElseThrow(() -> new UserException("Wrong user"))))
                .orElseThrow(() -> new EventException("event dont exist"));
    }

    @Override
    public EventDto saveParticipation(Long eventId) {
        String loggedUserName = userContextHolder.getLoggedUserName();
        User user = userRepository.findByEmail(loggedUserName).orElseThrow(() -> new UserException("Wrong user"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventException("evet dont exist"));
        Set<User> participants = event.getParticipants();
        boolean isAddedToEvent = participants.add(user);
        log.info("is user added to event : " + isAddedToEvent);
        event.setParticipants(participants);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.fromEventToEventDto(savedEvent);


    }

    @Override
    public EventDto deleteParticipation(Long eventId) {
        String loggedUserName = userContextHolder.getLoggedUserName();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventException("Event not found"));
        Set<User> participants = event.getParticipants();
        participants.removeIf(e -> e.getEmail().equals(loggedUserName));
        event.setParticipants(participants);
        return eventMapper.fromEventToEventDto(eventRepository.save(event));
    }
}
