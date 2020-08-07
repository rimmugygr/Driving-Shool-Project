package driving.school.services;


import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.Teacher;
import driving.school.repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherService {
    TeacherRepo teacherRepo;
    UserService userService;

    public List<Teacher> getAllTeacher() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() ->new ResourcesNotFound("Teacher on Id '"+ id +"' not exist"));
    }

    public Long addTeacher(Teacher teacher){
        isUniqueUsername(teacher);
        teacherRepo.save(teacher);
        return teacher.getId();
    }

    public void putTeacherById(Long id, Teacher newTeacher) {
        Teacher oldTeacher = getTeacherById(id);
        isValidUsername(newTeacher,oldTeacher);
        newTeacher.setId(id);
        teacherRepo.save(newTeacher);
    }

    public void deleteTeacherById(Long id) {
        teacherRepo.deleteById(id);
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
}
