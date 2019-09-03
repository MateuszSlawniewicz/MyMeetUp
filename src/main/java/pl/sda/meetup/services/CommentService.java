package pl.sda.meetup.services;

import pl.sda.meetup.dto.CommentDto;
import pl.sda.meetup.dto.EventDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(String comment, Long eventId);

    List<CommentDto> showAllComments(Long eventId);

    CommentDto updateComment(CommentDto commentDto);

    Long deleteComment(Long id);
}
