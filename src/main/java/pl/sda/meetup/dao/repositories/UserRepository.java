package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
