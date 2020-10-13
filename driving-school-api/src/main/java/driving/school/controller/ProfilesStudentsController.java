package driving.school.controller;

import driving.school.dto.StudentDto;
import driving.school.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/manage/students")
public class ProfilesStudentsController {
    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getStudent(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto patchStudent(@PathVariable long id,
                                   @RequestBody StudentDto studentDto) {
        return studentService.putStudentById(id, studentDto);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto postStudent(@RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto deleteStudent(@PathVariable long id) {
        return studentService.deleteStudentById(id);
    }
}
