package driving.school.controller;

import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.AvailableDate;
import driving.school.model.Views;
import driving.school.services.AvailableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AvailableTeacherController {
    AvailableService availableService;

    public AvailableTeacherController(AvailableService availableService) {
        this.availableService = availableService;
    }

    @GetMapping("/teacher/{teacherId}/available")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> getAvailableRideByTeacher(@PathVariable String teacherId) {
        return availableService.getAllAvailableDateByTeacher(teacherId);
    }

    @GetMapping("/teacher/available")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> getAllAvailableRide() {
        return availableService.getAllAvailableDate();
    }

    @PostMapping("/teacher/{teacherId}/available")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void postAvailableRideByTeacher(@PathVariable String teacherId,
                                  @RequestBody AvailableDate availableDate) throws SQLIntegrityConstraintViolationException {
        availableService.addAvailableDateByTeacher(teacherId, availableDate);
    }

    @PostMapping("/teacher/{teacherId}/available/list")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void postAvailableRideByTeacher(@PathVariable String teacherId,
                                  @RequestBody List<AvailableDate> availableDate) throws SQLIntegrityConstraintViolationException {
        availableService.addAvailableDateByTeacher(teacherId, availableDate);
    }

    @DeleteMapping("/teacher/{teacherId}/available/{availableId}")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteAvailableRideByTeacher(@PathVariable String teacherId,
                                             @PathVariable String availableId) {
        availableService.deleteAvailableDateByTeacher(availableId);
    }

    @DeleteMapping("/teacher/{teacherId}/available/list")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteAvailableRideByTeacher(@PathVariable String teacherId,
                                             @RequestBody List<String> availableIdList) {
        availableService.deleteAvailableDateByTeacher(availableIdList);
    }
}
