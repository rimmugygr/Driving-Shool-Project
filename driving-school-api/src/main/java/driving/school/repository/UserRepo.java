package driving.school.repository;

import driving.school.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {

    boolean existsByUsername(String username);
    User findUserByUsername(String username);
}
