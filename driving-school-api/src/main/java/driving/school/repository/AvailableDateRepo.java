package driving.school.repository;


import driving.school.model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {
    Stream<AvailableDate> streamAvailableDateByTeacherIdAndDate(Long id, Date date);
    List<AvailableDate> findAllByTeacherIdAndReservedIsFalse(Long id);
    List<AvailableDate> findAllByReservedIsFalse();
    void deleteAllByDateIsBefore(Date date);
    void deleteAvailableDatesByTeacherId(Long teacherId);
}
