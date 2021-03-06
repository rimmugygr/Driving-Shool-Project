package driving.school.repository;

import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepo extends JpaRepository<Authority, String> {
    Optional<Authority> findByName(Role role);
}
