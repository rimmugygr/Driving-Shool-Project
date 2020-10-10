package driving.school.services;

import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import driving.school.model.user.Student;
import driving.school.model.user.User;
import driving.school.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student getStudentById(long id)  {
        return studentRepo.findById(id)
                .orElseThrow(() ->new ResourcesNotFound("Student on Id '"+ id +"' not exist"));
    }

    public Student addStudent(Student student)  {
        isUniqueUsername(student);
        student.setUser(getUserWithAuthorityAndPasswordEncode(student));
        return  studentRepo.save(student);
    }

    public Student putStudentById(long id, Student newStudent) {
        Student oldStudent = getStudentById(id);
        isValidUsername(newStudent, oldStudent);
        newStudent.setId(id);
        newStudent.setUser(getUserWithAuthorityAndPasswordEncode(newStudent));
        return studentRepo.save(newStudent);
    }

    private User getUserWithAuthorityAndPasswordEncode(Student student) {
        User user = student.getUser();
        user.setRoles(Set.of(Authority.builder().name(Role.STUDENT).build()));
        user.setPassword(encoder.encode(student.getUser().getPassword()));
        return user;
    }

    public void deleteStudentById(long id) {
        deleteUserByStudentId(id);
        studentRepo.deleteById(id);
    }

    private void deleteUserByStudentId(long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFound("Student on Id '" + id + "' not exist"));
        userService.deleteUser(student.getUser());
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
            if(!userService.isUniqueUsername(student.getUser().getUsername()))
                throw new DuplicateUniqueKey("Username '" + student.getUser().getUsername() + "' already exist");
        }
    }

    public Student getStudentByUserId(Long userId) {
        return studentRepo.getByUserId(userId);
    }
}
