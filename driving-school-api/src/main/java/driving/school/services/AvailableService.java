package driving.school.services;


import driving.school.model.AvailableDate;
import driving.school.model.user.Teacher;
import driving.school.repository.AvailableDateRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AvailableService {
    AvailableDateRepo availableDateRepo;

    public long addAvailableDateByTeacher(long teacherId, AvailableDate availableDate) throws SQLIntegrityConstraintViolationException {
        availableDate.setTeacher(new Teacher(teacherId));
        availableDateRepo.save(availableDate);
        return availableDate.getId();
    }

    public void addAvailableDateByTeacher(long teacherId, List<AvailableDate> availableDate) throws SQLIntegrityConstraintViolationException {
        availableDate.forEach(x->x.setTeacher(new Teacher(teacherId)));
        availableDateRepo.saveAll(availableDate);
    }

    public void deleteAvailableDateByTeacher(long availableId) {
        availableDateRepo.deleteById(availableId);
    }

    public void deleteAvailableDateByTeacher(List<Long> availableId) {
        availableId.forEach(x-> availableDateRepo.deleteById(x));
    }

    public void deleteAllAvailableDateByTeacherId(long teacherId) {
        availableDateRepo.deleteAvailableDatesByTeacherId(teacherId);
    }

    public List<AvailableDate> getAllAvailableDateByTeacher(long teacherId) {
        return availableDateRepo.findAllByTeacherIdAndReservedIsFalse(teacherId);
    }

    public List<AvailableDate> getAllAvailableDate() {
        return availableDateRepo.findAllByReservedIsFalse();
    }

    @Transactional
    public void deleteOldAvailableDate() {
        availableDateRepo.deleteAllByDateIsBefore(Date.valueOf(LocalDate.now()));
    }
}
