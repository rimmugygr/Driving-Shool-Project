package driving.school.services;


import driving.school.model.user.*;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;

    public boolean isUniqueUsername(String username) {
        return !userRepository.existsByUsername(username);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUser(String username) {
        return userRepository.getOneByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: Username is not found."));
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }
}
