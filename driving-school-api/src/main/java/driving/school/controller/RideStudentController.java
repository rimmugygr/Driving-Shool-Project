package driving.school.controller;


import driving.school.controller.request.SimpleDateRequest;
import driving.school.dto.RideDateDto;
import driving.school.services.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rides")
public class RideStudentController {
    private final RideService rideService;

    @GetMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RideDateDto> getAllRideByStudent(@PathVariable Long studentId) {
        return rideService.getAllRideByStudentId(studentId);
    }

    @PostMapping("/teachers/{teacherId}/students/{studentId}/date")
    @ResponseStatus(HttpStatus.CREATED)
    public void postRideByStudent(@PathVariable(name = "teacherId") Long teacherId,
                                  @PathVariable(name = "studentId") Long studentId,
                                  @RequestBody SimpleDateRequest date) throws SQLIntegrityConstraintViolationException {
        rideService.addRideByStudent(studentId,teacherId, date);
    }

    @DeleteMapping("/teacher/{teacherId}/student/{studentId}/date")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteRideByStudent(@PathVariable(name = "teacherId") Long teacherId,
                                    @PathVariable(name = "studentId") Long studentId,
                                    @RequestBody SimpleDateRequest date) {
        rideService.deleteRideByStudent(studentId,teacherId, date);
    }

}
