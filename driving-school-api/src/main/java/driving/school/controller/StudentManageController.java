package driving.school.controller;

import driving.school.dto.StudentUserDto;
import driving.school.mapper.StudentMapper;
import driving.school.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/manage/students")
public class StudentManageController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentUserDto>> getAllStudent() {
        List<StudentUserDto> studentUserDto = studentService.getAllStudent().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(studentUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentUserDto> getStudent(@PathVariable long id) {
        return ResponseEntity.ok().body(studentMapper.map(studentService.getStudentById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> patchStudent(@PathVariable long id,
                             @RequestBody StudentUserDto studentUserDto) {
        studentService.putStudentById(id, studentMapper.map(studentUserDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Void> postStudent(@RequestBody StudentUserDto studentUserDto) {
        Long id = studentService.addStudent(studentMapper.map(studentUserDto));
        return ResponseEntity.created(URI.create("api/manage/students/"+ id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.notFound().build();
    }
}
