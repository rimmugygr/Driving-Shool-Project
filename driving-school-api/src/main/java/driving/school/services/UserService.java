package driving.school.services;

import driving.school.model.user.Student;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserRepo userRepo;

    public boolean isUsernameExist(String name) {
        return userRepo.existsByUsername(name);
    }
}
