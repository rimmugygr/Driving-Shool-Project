package driving.school.repository;

import driving.school.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {

    boolean existsByUsername(String username);
}
