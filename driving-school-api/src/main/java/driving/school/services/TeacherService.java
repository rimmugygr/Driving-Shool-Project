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

    public Long addTeacher(Teacher teacher){
        isUniqueUsername(teacher);
        teacherRepo.save(teacher);
        teacher.setUser(getUserWithAuthorityAndPasswordEncode(teacher));
        return teacher.getId();
    }

    public void putTeacherById(Long teacherId, Teacher newTeacher) {
        Teacher oldTeacher = getTeacherById(teacherId);
        isValidUsername(newTeacher,oldTeacher);
        newTeacher.setId(teacherId);
        newTeacher.setUser(getUserWithAuthorityAndPasswordEncode(newTeacher));
        teacherRepo.save(newTeacher);
    }

    private User getUserWithAuthorityAndPasswordEncode(Teacher teacher) {
        User user = teacher.getUser();
        user.setRoles(Set.of(Authority.builder().name(Role.STUDENT).build()));
        user.setPassword(encoder.encode(teacher.getUser().getPassword()));
        return user;
    }

    public void deleteTeacherById(Long id) {
        deleteUserByStudentId(id);
        teacherRepo.deleteById(id);
    }

    private void deleteUserByStudentId(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFound("Teacher on Id '" + id + "' not exist"));
        userService.deleteUser(teacher.getUser());
    }

    //TODO case when oldTeacher username == null
    private void isValidUsername(Teacher newTeacher, Teacher oldTeacher) {
        if (newTeacher.getUser() != null){
            if (!oldTeacher.getUser().getUsername().equals(newTeacher.getUser().getUsername())) {
                isUniqueUsername(newTeacher);
            }
        }
    }

    private void isUniqueUsername(Teacher teacher) {
        if (teacher.getUser() != null) {
            if(!userService.isUniqueUsername(teacher.getUser()))
                throw new DuplicateUniqueKey("Username '" + teacher.getUser().getUsername() + "' already exist");
        }
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepo.getByUserId(userId);
    }
}
