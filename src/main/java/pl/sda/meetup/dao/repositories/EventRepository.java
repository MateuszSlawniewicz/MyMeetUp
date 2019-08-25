package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
