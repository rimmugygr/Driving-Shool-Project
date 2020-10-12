package driving.school.controller;

import driving.school.dto.StudentUserDto;
import driving.school.mapper.StudentMapper;
import driving.school.model.user.Student;
import driving.school.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/manage/students")
public class StudentManageController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentUserDto> getAllStudent() {
        return studentService.getAllStudent().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentUserDto getStudent(@PathVariable long id) {
        return studentMapper.map(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentUserDto patchStudent(@PathVariable long id,
                             @RequestBody StudentUserDto studentUserDto) {
        Student studentPuttied = studentService.putStudentById(id, studentMapper.map(studentUserDto));
        return studentMapper.map(studentPuttied);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StudentUserDto postStudent(@RequestBody StudentUserDto studentUserDto) {
        Student studentAdded = studentService.addStudent(studentMapper.map(studentUserDto));
        return studentMapper.map(studentAdded);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentUserDto deleteStudent(@PathVariable long id) {
        return studentService.deleteStudentById(id);
    }
}
