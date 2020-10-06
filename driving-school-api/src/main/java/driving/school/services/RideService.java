package driving.school.services;


import driving.school.controller.request.SimpleDateRequest;
import driving.school.dto.RideDateDto;
import driving.school.mapper.RideDateMapper;
import driving.school.model.RideDate;
import driving.school.model.user.Student;
import driving.school.model.user.Teacher;
import driving.school.repository.AvailableDateRepo;
import driving.school.repository.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideService {
    private final RideRepo rideRepo;
    private final RideDateMapper rideDateMapper;
    private final AvailableDateRepo availableDateRepo;

    public List<RideDateDto> getAllRide() {
        return rideRepo.findAll().stream()
                .map(rideDateMapper::map)
                .collect(Collectors.toList());
    }

    public RideDateDto getRideById(Long id) throws NoSuchElementException {
        return rideRepo.findById(id)
                .map(rideDateMapper::map)
                .orElseThrow(()->new NoSuchElementException("#Errror Ride on Id '"+ id +"' not exist in data base"));
    }

    public List<RideDateDto> getAllRideByStudentId(Long id) {
        return rideRepo.getAllByStudentId(id).stream()
                .map(rideDateMapper::map)
                .collect(Collectors.toList());
    }

    public List<RideDateDto> getAllRideByTeacherId(Long id) {
        return rideRepo.getAllByTeacherId(id).stream()
                .map(rideDateMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addRideByStudent(Long studentId, Long teacherId, SimpleDateRequest dateRide) throws SQLIntegrityConstraintViolationException {
        RideDate newRideDate = new RideDate(
                dateRide.getDate(),
                new Student(studentId),
                new Teacher(teacherId)
        );
        rideRepo.save(newRideDate);
        availableDateRepo.streamAvailableDateByTeacherIdAndDate(teacherId, dateRide.getDate())
                .forEach(x->x.setReserved(true));
    }

    @Transactional
    public void deleteRideByStudent(Long studentId, Long teacherId, SimpleDateRequest dateRide) {
        RideDate newRideDate = new RideDate(
                dateRide.getDate(),
                new Student(studentId),
                new Teacher(teacherId)
        );
        rideRepo.delete(newRideDate);
        availableDateRepo.streamAvailableDateByTeacherIdAndDate(teacherId, dateRide.getDate())
                .forEach(x->x.setReserved(false));
    }

}
