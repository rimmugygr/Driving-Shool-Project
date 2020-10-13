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
    private final TeacherMapper teacherMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDto> getTeacher() {
        return teacherService.getAllTeacher().stream()
                .map(teacherMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto getTeacher(@PathVariable long id) {
        return teacherMapper.map(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto patchTeacher(@PathVariable long id,
                                   @RequestBody TeacherDto teacherDto){
        Teacher teacherPuttied = teacherService.putTeacherById(id, teacherMapper.map(teacherDto));
        return teacherMapper.map(teacherPuttied);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDto postTeacher(@RequestBody TeacherDto teacherDto) {
        System.out.println(teacherDto);
        Teacher teacherPosted =  teacherService.addTeacher(teacherMapper.map(teacherDto));
        return teacherMapper.map(teacherPosted);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto deleteTeacher(@PathVariable long id) {
        return teacherService.deleteTeacherById(id);
    }
}
