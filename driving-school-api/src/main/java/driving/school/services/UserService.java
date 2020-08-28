package driving.school.services;


import driving.school.model.user.*;
import driving.school.repository.AuthorityRepo;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;
    private final AuthorityRepo authorityRepository;

    public boolean isUniqueUsername(User user) {
        User userResult = userRepository.findUserByUsername(user.getUsername());
        return userResult == null;
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


}
