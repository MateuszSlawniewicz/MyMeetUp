package pl.sda.meetup.services;

import pl.sda.meetup.dto.CommentDto;
import pl.sda.meetup.dto.EventDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto);
    List<CommentDto> showAllComments(EventDto eventDto);
    CommentDto updateComment(CommentDto commentDto);
    Long deleteComment(Long id);
}
