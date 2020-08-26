package driving.school.controller;


import driving.school.model.RideDate;
import driving.school.services.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideStudentController {
    RideService rideService;

    public RideStudentController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/student/{studentId}")

    @ResponseStatus(HttpStatus.OK)
    public List<RideDate> getAllRideByStudent(@PathVariable String studentId) {
        return rideService.getAllRideByStudentId(studentId);
    }

    @PostMapping("/teacher/{teacherId}/student/{studentId}")

    @ResponseStatus(HttpStatus.CREATED)
    public void postRideByStudent(@PathVariable(name = "teacherId") String teacherId,
                                  @PathVariable(name = "studentId") String studentId,
                                  @RequestBody RideDate rideDate) throws SQLIntegrityConstraintViolationException {
        rideService.addRideByStudent(studentId,teacherId, rideDate);
    }

    @DeleteMapping("/teacher/{teacherId}/student/{studentId}")

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteRideByStudent(@PathVariable(name = "teacherId") String teacherId,
                                    @PathVariable(name = "studentId") String studentId,
                                    @RequestBody RideDate rideDate) {
        rideService.deleteRideByStudent(studentId,teacherId, rideDate);
    }

}
