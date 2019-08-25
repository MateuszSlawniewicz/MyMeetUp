package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
