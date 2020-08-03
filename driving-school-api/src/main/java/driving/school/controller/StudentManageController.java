package driving.school.controller;

import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.Views;
import driving.school.model.user.Student;
import driving.school.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/student")
public class StudentManageController {
    StudentService studentService;

    public StudentManageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @JsonView(Views.AdminView.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    @JsonView(Views.AdminView.class)
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable String id) throws NoSuchElementException {
        return studentService.getStudentById(id);
    }

    @PatchMapping("/{id}")
    @JsonView(Views.AdminView.class)
    @ResponseStatus(HttpStatus.OK)
    public void patchStudent(@PathVariable String id,
                             @RequestBody Student student) throws NoSuchElementException, SQLIntegrityConstraintViolationException {
        studentService.editStudentById(id, student);
    }

    @PostMapping()
    @JsonView(Views.AdminView.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student) throws SQLIntegrityConstraintViolationException {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    @JsonView(Views.AdminView.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
    }


}
