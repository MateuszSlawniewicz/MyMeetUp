package pl.sda.meetup.mappers;

import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.Comment;
import pl.sda.meetup.dto.CommentDto;
@Component
public class CommentMapper {
    public CommentDto fromCommentToCommentDto(Comment comment) {
        return null;
    }

    public Comment fromCommentDtoToComment(CommentDto commentDto) {
        return null;
    }
}
