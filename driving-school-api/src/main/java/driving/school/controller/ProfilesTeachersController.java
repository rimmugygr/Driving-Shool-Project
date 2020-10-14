package driving.school.controller;

import driving.school.dto.TeacherDto;
import driving.school.mapper.TeacherMapper;
import driving.school.model.Teacher;
import driving.school.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manage/teachers")
public class ProfilesTeachersController {
    private final TeacherService teacherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDto> getTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto getTeacher(@PathVariable long id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto patchTeacher(@PathVariable long id,
                                   @RequestBody TeacherDto teacherDto){
        return teacherService.putTeacherById(id, teacherDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDto postTeacher(@RequestBody TeacherDto teacherDto) {
        return teacherService.addTeacher(teacherDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto deleteTeacher(@PathVariable long id) {
        return teacherService.deleteTeacherById(id);
    }
}
