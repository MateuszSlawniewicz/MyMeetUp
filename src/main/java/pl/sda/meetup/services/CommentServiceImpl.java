package pl.sda.meetup.services;

import org.springframework.stereotype.Service;
import pl.sda.meetup.dto.CommentDto;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public List<CommentDto> showAllComments() {
        return null;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public Long deleteComment(Long id) {
        return null;
    }
}
