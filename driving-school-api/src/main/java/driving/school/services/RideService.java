package driving.school.services;


import driving.school.model.RideDate;
import driving.school.model.user.Student;
import driving.school.model.user.Teacher;
import driving.school.repository.AvailableDateRepo;
import driving.school.repository.RideRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RideService {
    RideRepo rideRepo;
    AvailableDateRepo availableDateRepo;

    public RideService(RideRepo rideRepo, AvailableDateRepo availableDateRepo) {
        this.rideRepo = rideRepo;
        this.availableDateRepo = availableDateRepo;
    }

    public List<RideDate> getAllRide() {
        return rideRepo.findAll();
    }

    public RideDate getRideById(String id) throws NoSuchElementException {
        return rideRepo.findById(Long.parseLong(id))
                .orElseThrow(()->new NoSuchElementException("#Errror Ride on Id '"+ id +"' not exist in data base"));
    }

    public List<RideDate> getAllRideByStudentId(String id) {
        return rideRepo.getAllByStudentId(Long.valueOf(id));
    }

    public List<RideDate> getAllRideByTeacherId(String id) {
        return rideRepo.getAllByTeacherId(Long.valueOf(id));
    }

    @Transactional
    public void addRideByStudent(String studentId, String teacherId, RideDate rideDate) throws SQLIntegrityConstraintViolationException {
        RideDate newRideDate = new RideDate(
                rideDate.getDate(),
                new Student(Long.parseLong(studentId)),
                new Teacher(Long.parseLong(teacherId))
        );
        rideRepo.save(newRideDate);
        availableDateRepo.streamAvailableDateByTeacherIdAndDate(Long.parseLong(teacherId), rideDate.getDate())
                .forEach(x->x.setReserved(true));
    }

    @Transactional
    public void deleteRideByStudent(String studentId, String teacherId, RideDate rideDate) {
        RideDate newRideDate = new RideDate(
                rideDate.getDate(),
                new Student(Long.parseLong(studentId)),
                new Teacher(Long.parseLong(teacherId))
        );
        rideRepo.delete(newRideDate);
        availableDateRepo.streamAvailableDateByTeacherIdAndDate(Long.parseLong(teacherId), rideDate.getDate())
                .forEach(x->x.setReserved(false));
    }

}
