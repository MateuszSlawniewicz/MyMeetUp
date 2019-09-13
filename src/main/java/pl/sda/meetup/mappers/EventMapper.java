package pl.sda.meetup.mappers;

import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dto.EventDto;

import java.util.stream.Collectors;


@Component
public class EventMapper {

    private final UserMapper userMapper;

    public EventMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public Event fromEventDtoToEvent(EventDto eventDto) {
        return Event.builder()
                .description(eventDto.getDescription())
                .end(eventDto.getEnd())
                .start(eventDto.getStart())
                .title(eventDto.getTitle())
                .build();
    }

    public EventDto fromEventToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .description(event.getDescription())
                .end(event.getEnd())
                .start(event.getStart())
                .title(event.getTitle())
                .participants(event.getParticipants().stream()
                        .map(userMapper::fromUserToUserDto)
                        .collect(Collectors.toSet()))
                .userDto(userMapper.fromUserToUserDto(event.getUser()))
                .build();


    }

}
