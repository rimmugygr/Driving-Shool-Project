package driving.school.controller;

import driving.school.dto.RideDateDto;
import driving.school.model.RideDate;
import driving.school.services.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RideTeacherController {
    private final RideService rideService;

    @GetMapping("/ride/teacher/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RideDateDto> getAllRideByTeacher(@PathVariable Long teacherId) {
        return rideService.getAllRideByTeacherId(teacherId);
    }

}
