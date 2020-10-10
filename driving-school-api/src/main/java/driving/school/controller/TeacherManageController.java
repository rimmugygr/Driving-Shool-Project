package driving.school.controller;

import driving.school.dto.TeacherUserDto;
import driving.school.mapper.TeacherMapper;
import driving.school.model.user.Teacher;
import driving.school.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manage/teachers")
public class TeacherManageController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherUserDto> getTeacher() {
        return teacherService.getAllTeacher().stream()
                .map(teacherMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherUserDto getTeacher(@PathVariable long id) {
        return teacherMapper.map(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherUserDto patchTeacher(@PathVariable long id,
                                       @RequestBody TeacherUserDto teacherUserDto){
        Teacher teacherPuttied = teacherService.putTeacherById(id, teacherMapper.map(teacherUserDto));
        return teacherMapper.map(teacherPuttied);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherUserDto postTeacher(@RequestBody TeacherUserDto teacherUserDto) {
        System.out.println(teacherUserDto);
        Teacher teacherPosted =  teacherService.addTeacher(teacherMapper.map(teacherUserDto));
        return teacherMapper.map(teacherPosted);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherUserDto deleteTeacher(@PathVariable long id) {
        Teacher teacherDeleted =  teacherService.deleteTeacherById(id);
        return teacherMapper.map(teacherDeleted);
    }
}
