package pl.sda.meetup.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.dao.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
