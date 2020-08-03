package driving.school.services;

import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.model.user.Student;
import driving.school.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final UserService userService;


    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student getStudentById(long id)throws NoSuchElementException  {
        return studentRepo.findById(id)
                .orElseThrow(() ->new ResourcesNotFound("Student on Id '"+ id +"' not exist"));
    }

    public Long addStudent(Student student) throws SQLIntegrityConstraintViolationException {
        if(userService.isUsernameExist(student.getUser().getUsername()))
            throw new DuplicateUniqueKey("Username '" + student.getUser().getUsername() + "' already exist");
        studentRepo.save(student);
        return student.getId();
    }



    public void editStudentById(long id, Student student) throws NoSuchElementException, SQLIntegrityConstraintViolationException {
        if(userService.isUsernameExist(student.getUser().getUsername()))
            throw new DuplicateUniqueKey("Username '" + student.getUser().getUsername() + "' already exist");
        if (studentRepo.existsById(id)) {
            studentRepo.save(student);
        } else throw new ResourcesNotFound("Student on id '"+ id +"' not exist");
    }

    public void deleteStudentById(long id) {
        studentRepo.deleteById(id);
    }
}
