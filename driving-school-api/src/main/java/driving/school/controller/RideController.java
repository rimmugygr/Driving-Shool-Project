package driving.school.controller;


import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.RideDate;
import driving.school.model.Views;
import driving.school.services.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.OK)
    public List<RideDate> getAllRide() {
        return rideService.getAllRide();
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserView.class)
    @ResponseStatus(HttpStatus.OK)
    public RideDate getRide(@PathVariable String id) throws NoSuchElementException {
        return rideService.getRideById(id);
    }



}
