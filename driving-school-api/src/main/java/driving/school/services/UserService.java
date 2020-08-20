package driving.school.services;

import driving.school.model.user.User;
import driving.school.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public boolean isUniqueUsername(User user) {
        User userResult = userRepository.findUserByUsername(user.getUsername());
        return userResult == null;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
