package driving.school.controller;


import driving.school.dto.AvailableDateDto;
import driving.school.mapper.AvailableMapper;
import driving.school.model.AvailableDate;
import driving.school.services.AvailableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class AvailableDateController {
    AvailableService availableService;
    AvailableMapper availableMapper;

    @GetMapping("/{teacherId}/available")
    public ResponseEntity<List<AvailableDateDto>> getAvailableRideByTeacher(@PathVariable long teacherId) {
        List<AvailableDateDto> availableDateDto = availableService.getAllAvailableDateByTeacher(teacherId).stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(availableDateDto);
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AvailableDateDto>> getAllAvailableRide() {
        List<AvailableDateDto> availableDateDto = availableService.getAllAvailableDate().stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(availableDateDto);
    }

    @PostMapping("/available")
    public ResponseEntity<Void> postAvailableRideByTeacher(@RequestBody AvailableDateDto availableDateDto) {
        long id = availableService.addAvailableDate(availableMapper.map(availableDateDto));
        return ResponseEntity.created(URI.create("/api/teachers/available")).build();
    }

    @PostMapping("/available/list")
    public ResponseEntity<Void> postAvailableRidesByTeacher(
                                  @RequestBody List<AvailableDateDto> availableDateDto) throws SQLIntegrityConstraintViolationException {
        List<AvailableDate> availableDate = availableDateDto.stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
        availableService.addAvailableDate(availableDate);
        return ResponseEntity.created(URI.create("")).body(null);
    }

    @DeleteMapping("/available/{availableId}")
    public ResponseEntity<Void> deleteAvailableRideByTeacher(@PathVariable long availableId) {
        availableService.deleteAvailableDateByTeacher(availableId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/available/list")
    public ResponseEntity<Void> deleteAvailableRideByTeacher(@RequestBody List<Long> availableIdList) {
        availableService.deleteAvailableDateByTeacher(availableIdList);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{teacherId}/available")
    public ResponseEntity<Void> deleteAllAvailableRideByTeacherId( @PathVariable long teacherId) {
        availableService.deleteAllAvailableDateByTeacherId(teacherId);
        return ResponseEntity.notFound().build();
    }
}

