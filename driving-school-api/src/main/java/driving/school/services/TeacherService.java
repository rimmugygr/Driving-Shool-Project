package driving.school.services;


import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.*;
import driving.school.repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public List<Teacher> getAllTeacher() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() ->new ResourcesNotFound("Teacher on id '"+ id +"' not exist"));
    }

    public Teacher addTeacher(Teacher teacher){
        if(!userService.isUniqueUsername(teacher.getUser().getUsername()))
            throw new DuplicateUniqueKey("Username '" + teacher.getUser().getUsername() + "' already exist");
        teacher.setUser(getUserWithAuthorityAndPasswordEncode(teacher));
        return teacherRepo.save(teacher);
    }

    public Teacher putTeacherById(Long teacherId, Teacher newTeacher) {
        Teacher oldTeacher = getTeacherById(teacherId);
        if (!oldTeacher.getUser().getUsername().equals(newTeacher.getUser().getUsername())) {
            if(!userService.isUniqueUsername(newTeacher.getUser().getUsername()))
                throw new DuplicateUniqueKey("Username '" + newTeacher.getUser().getUsername() + "' already exist");
        }

        newTeacher.setId(teacherId);
        newTeacher.setUser(getUserWithAuthorityAndPasswordEncode(newTeacher));
        return teacherRepo.save(newTeacher);
    }

    private User getUserWithAuthorityAndPasswordEncode(Teacher teacher) {
        User user = teacher.getUser();
        user.setRoles(Set.of(Authority.builder().name(Role.STUDENT).build()));
        user.setPassword(encoder.encode(teacher.getUser().getPassword()));
        return user;
    }

    public Teacher deleteTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFound("Teacher on Id '" + id + "' not exist"));
        userService.deleteUser(teacher.getUser());
        teacherRepo.deleteById(id);
        return teacher;
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepo.getByUserId(userId);
    }
}
