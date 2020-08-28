package driving.school.services;


import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.*;
import driving.school.repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final UserService userService;

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
        setAuthority(teacher);
        return teacher.getId();
    }

    public void putTeacherById(Long teacherId, Teacher newTeacher) {
        Teacher oldTeacher = getTeacherById(teacherId);
        isValidUsername(newTeacher,oldTeacher);
        setParameter(teacherId, newTeacher);
        teacherRepo.save(newTeacher);
    }

    private void setParameter(Long teacherId, Teacher newTeacher) {
        newTeacher.setId(teacherId);
        User user = newTeacher.getUser();
        user.setRoles(Set.of(Authority.builder().name(Role.TEACHER).build()));
        user.setPassword(newTeacher.getUser().getPassword());
        newTeacher.setUser(user);
    }

    private void setAuthority(Teacher teacher) {
        User user = teacher.getUser();
        user.setRoles(Set.of(Authority.builder().name(Role.TEACHER).build()));
        teacher.setUser(user);
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
