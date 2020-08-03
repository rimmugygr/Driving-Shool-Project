package driving.school.services;


import driving.school.model.AvailableDate;
import driving.school.model.user.Teacher;
import driving.school.repository.AvailableDateRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
public class AvailableService {

    AvailableDateRepo availableDateRepo;

    public AvailableService(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    public void addAvailableDateByTeacher(String teacherId, AvailableDate availableDate) throws SQLIntegrityConstraintViolationException {
        availableDate.setTeacher(new Teacher(Long.parseLong(teacherId)));
        availableDateRepo.save(availableDate);
    }

    public void addAvailableDateByTeacher(String teacherId, List<AvailableDate> availableDate) throws SQLIntegrityConstraintViolationException {
        availableDate.forEach(x->x.setTeacher(new Teacher(Long.parseLong(teacherId))));
        availableDateRepo.saveAll(availableDate);
    }

    public void deleteAvailableDateByTeacher(String availableId) {
        availableDateRepo.deleteById(Long.parseLong(availableId));
    }

    public void deleteAvailableDateByTeacher(List<String> availableId) {
        availableId.forEach(
                x-> availableDateRepo.deleteById(Long.parseLong(x))
        );
    }

    public List<AvailableDate> getAllAvailableDateByTeacher(String teacherId) {
        return availableDateRepo.findAllByTeacherIdAndReservedIsFalse(Long.parseLong(teacherId));
    }

    public List<AvailableDate> getAllAvailableDate() {
        return availableDateRepo.findAllByReservedIsFalse();
    }

    @Transactional
    public void deleteOldAvailableDate() {
        availableDateRepo.deleteAllByDateIsBefore(Date.valueOf(LocalDate.now()));
    }
}
