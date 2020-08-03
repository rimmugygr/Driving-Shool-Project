package driving.school.controller;

import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.user.Teacher;
import driving.school.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/teacher")
public class TeacherManageController {
    TeacherService teacherService;

    public TeacherManageController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping

    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")

    @ResponseStatus(HttpStatus.OK)
    public Teacher getTeacher(@PathVariable String id) throws NoSuchElementException {
        return teacherService.getTeacherById(id);
    }

    @PatchMapping("/{id}")

    @ResponseStatus(HttpStatus.OK)
    public void patchTeacher(@PathVariable String id,
                             @RequestBody Teacher teacher) throws NoSuchElementException, SQLIntegrityConstraintViolationException {
        teacherService.editTeacherById(id, teacher);
    }

    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public Teacher postTeacher(@RequestBody Teacher teacher) throws SQLIntegrityConstraintViolationException {
        return teacherService.addTeacher(teacher);
    }

    @DeleteMapping("/{id}")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacherById(id);
    }
}
