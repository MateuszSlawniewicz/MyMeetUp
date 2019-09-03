package pl.sda.meetup.mappers;

import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dto.EventDto;


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
                .userLoginDto(userMapper.fromUserToUserLoginDto(event.getUser()))
                .build();


    }

}
