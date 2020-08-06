package driving.school.services;

import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.Student;
import driving.school.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final UserService userService;

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student getStudentById(long id)  {
        return studentRepo.findById(id)
                .orElseThrow(() ->new ResourcesNotFound("Student on Id '"+ id +"' not exist"));
    }

    public Long addStudent(Student student)  {
        isUniqueUsername(student);
        return  studentRepo.save(student).getId();
    }

    public void putStudentById(long id, Student newStudent) {
        Student oldStudent = getStudentById(id);
        isValidUsername(newStudent, oldStudent);
        newStudent.setId(id);
        studentRepo.save(newStudent);
    }

    public void deleteStudentById(long id) {
        studentRepo.deleteById(id);
    }

    private void isValidUsername(Student newStudent, Student oldStudent) {
        if (isHaveUsername(oldStudent)){
            isUniqueUsername(newStudent);
        } else if (newStudent.getUser() != null){
            if (isDifferentUsername(newStudent, oldStudent)) {
                isUniqueUsername(newStudent);
            }
        }
    }

    private boolean isDifferentUsername(Student newStudent, Student oldStudent) {
        return !oldStudent.getUser().getUsername().equals(newStudent.getUser().getUsername());
    }

    private boolean isHaveUsername(Student oldStudent) {
        return oldStudent.getUser() == null || oldStudent.getUser().getUsername() == null;
    }

    private void isUniqueUsername(Student student) {
        if (student.getUser() != null) {
            if(!userService.isUniqueUsername(student.getUser()))
                throw new DuplicateUniqueKey("Username '" + student.getUser().getUsername() + "' already exist");
        }
    }
}
