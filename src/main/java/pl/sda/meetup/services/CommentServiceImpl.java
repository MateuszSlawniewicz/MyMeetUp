package pl.sda.meetup.services;

import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.Comment;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dao.repositories.CommentRepository;
import pl.sda.meetup.dto.CommentDto;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.exceptions.CommentTableExcepion;
import pl.sda.meetup.mappers.CommentMapper;
import pl.sda.meetup.mappers.EventMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final EventMapper eventMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, EventMapper eventMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.eventMapper = eventMapper;
    }


    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        return commentMapper.fromCommentToCommentDto(commentRepository.save(commentMapper.fromCommentDtoToComment(commentDto)));
    }


    @Override
    public List<CommentDto> showAllComments(EventDto eventDto) {
        Event event = eventMapper.fromEventDtoToEvent(eventDto);
        return commentRepository.findAllByEvent(event)
                .stream()
                .map(commentMapper::fromCommentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        //todo
//first find and then get id from db and set to dto obj.
        return commentMapper.fromCommentToCommentDto(commentRepository.save(commentMapper.fromCommentDtoToComment(commentDto)));
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return deleteExistCommentAndReturnId(id);
        } else {
            throw new CommentTableExcepion("comment not found");
        }


    }

    private Long deleteExistCommentAndReturnId(Long id) {
        commentRepository.deleteById(id);
        if (!commentRepository.findById(id).isPresent()) {
            return id;
        } else {
            throw new CommentTableExcepion("exception during deleting");
        }
    }


}
