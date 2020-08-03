package driving.school.services;

import driving.school.model.user.Student;
import driving.school.repository.StudentRepo;
import driving.school.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    StudentRepo studentRepo;
    UserRepo userRepo;

    public StudentService(StudentRepo studentRepo, UserRepo userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student getStudentById(String id)throws NoSuchElementException  {
        return studentRepo.findById(Long.valueOf(id))
                .orElseThrow(() ->new NoSuchElementException("#Errror# Student on Id '"+ id +"' not exist in data base"));
    }

    public Student addStudent(Student student) throws SQLIntegrityConstraintViolationException {
        studentRepo.save(student);
        return student;
    }

    public void editStudentById(String id, Student student) throws NoSuchElementException, SQLIntegrityConstraintViolationException {

        if (studentRepo.existsById(Long.valueOf(id))) {
            studentRepo.save(student);
        } else throw new NoSuchElementException("#Errror# Student on Id '"+ id +"' not exist in data base");
    }

    public void deleteStudentById(String id) {
        studentRepo.deleteById(Long.valueOf(id));
    }
}
