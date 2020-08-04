package driving.school.controller;

import driving.school.dto.TeacherUserDto;
import driving.school.mapper.TeacherMapper;
import driving.school.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/manage/teachers")
public class TeacherManageController {
    TeacherService teacherService;
    TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<List<TeacherUserDto>> getAllTeacher() {
        List<TeacherUserDto> teacherUserDto = teacherService.getAllTeacher().stream()
                .map(teacherMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(teacherUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherUserDto> getTeacher(@PathVariable long id) {
        return ResponseEntity.ok().body(teacherMapper.map(teacherService.getTeacherById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchTeacher(@PathVariable long id,
                             @RequestBody TeacherUserDto teacherUserDto){
        teacherService.editTeacherById(id, teacherMapper.map(teacherUserDto));
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    public ResponseEntity<Void> postTeacher(@RequestBody TeacherUserDto teacherUserDto) {
        Long id = teacherService.addTeacher(teacherMapper.map(teacherUserDto));
        return ResponseEntity.created(URI.create("/api/manage/teacher/"+id)).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.notFound().build();
    }
}
