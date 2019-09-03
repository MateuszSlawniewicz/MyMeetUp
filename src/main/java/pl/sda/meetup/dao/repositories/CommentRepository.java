package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.Comment;
import pl.sda.meetup.dao.model.Event;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEventOrderByDateOfCreation(Event event);
}
