package driving.school.controller;


import driving.school.dto.AvailableDateDto;
import driving.school.services.AvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class AvailableDateController {
    private final AvailableService availableService;

    @GetMapping("/{teacherId}/available")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateDto> getAvailableRideByTeacher(@PathVariable long teacherId) {
        return availableService.getAllAvailableDateByTeacher(teacherId);
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateDto> getAllAvailableRide() {
        return availableService.getAllAvailableDate();
    }

    @PostMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateDto postAvailableRideByTeacher(@RequestBody AvailableDateDto availableDateDto) {
        return availableService.addAvailableDate(availableDateDto);
    }

    @PostMapping("/available/list")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateDto> postAvailableRidesByTeacher(@RequestBody List<AvailableDateDto> availableDateDto)
            throws SQLIntegrityConstraintViolationException {
        return availableService.addAvailableDate(availableDateDto);
    }

    @DeleteMapping("/available/{availableId}")
    public AvailableDateDto deleteAvailableRideByTeacher(@PathVariable long availableId) {
        return availableService.deleteAvailableDateByTeacher(availableId);
    }

    @DeleteMapping("/available/list")
    public List<AvailableDateDto> deleteAvailableRideByTeacher(@RequestBody List<Long> availableIdList) {
        return availableService.deleteAvailableDateByTeacher(availableIdList);
    }

    @DeleteMapping("/{teacherId}/available")
    public List<AvailableDateDto> deleteAllAvailableRideByTeacherId( @PathVariable long teacherId) {
        return availableService.deleteAllAvailableDateByTeacherId(teacherId);
    }
}

