package pl.sda.meetup.mappers;

import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.Comment;
import pl.sda.meetup.dto.CommentDto;

@Component
public class CommentMapper {
    private final UserMapper userMapper;
    private final EventMapper eventMapper;

    public CommentMapper(UserMapper userMapper, EventMapper eventMapper) {
        this.userMapper = userMapper;
        this.eventMapper = eventMapper;
    }

    public CommentDto fromCommentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .dateOfCreation(comment.getDateOfCreation())
                .description(comment.getDescription())
                .userDto(userMapper.fromUserToUserDto(comment.getUser()))
                .eventDto(eventMapper.fromEventToEventDto(comment.getEvent()))
                .id(comment.getId())
                .build();

    }

}

