package driving.school.services;

import driving.school.model.user.User;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserRepo userRepo;

    public boolean isUniqueUsername(User user) {
        User userResult = userRepo.findUserByUsername(user.getUsername());
        return userResult == null;
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }
}
