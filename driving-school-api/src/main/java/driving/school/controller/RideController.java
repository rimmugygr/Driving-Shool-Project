package driving.school.controller;


import driving.school.dto.RideDateDto;
import driving.school.model.RideDate;
import driving.school.services.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final RideService rideService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RideDateDto> getAllRide() {
        return rideService.getAllRide();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RideDateDto getRide(@PathVariable Long id) throws NoSuchElementException {
        return rideService.getRideById(id);
    }



}
