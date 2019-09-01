package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByTitleIgnoreCase(String title);


}
