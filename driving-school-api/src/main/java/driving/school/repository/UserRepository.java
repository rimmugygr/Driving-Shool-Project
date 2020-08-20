package driving.school.repository;

import driving.school.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByUsername(String username);
    User findUserByUsername(String username);
    Optional<User> getOneByUsername(String username);

}
