package pl.sda.meetup.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.Comment;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.CommentRepository;
import pl.sda.meetup.dao.repositories.EventRepository;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.CommentDto;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.dto.UserLoginDto;
import pl.sda.meetup.exceptions.CommentTableExcepion;
import pl.sda.meetup.exceptions.EventException;
import pl.sda.meetup.exceptions.UserException;
import pl.sda.meetup.mappers.CommentMapper;
import pl.sda.meetup.mappers.EventMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, EventMapper eventMapper, UserRepository userRepository, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public CommentDto saveComment(String comment, Long eventId) {
        Comment commentToSave = Comment.builder()
                .user(getUserFromContext())
                .event(eventRepository.findById(eventId).orElseThrow(() -> new EventException("event dont exist")))
                .dateOfCreation(LocalDateTime.now())
                .description(comment)
                .build();
        return commentMapper.fromCommentToCommentDto(commentRepository.save(commentToSave));
    }


    @Override
    public List<CommentDto> showAllComments(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventException("there is no event with id: " + eventId));;
        return commentRepository.findAllByEventOrderByDateOfCreation(event)
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


    private User getUserFromContext() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException("user dont exist"));
    }


}
