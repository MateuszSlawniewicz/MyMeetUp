package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
