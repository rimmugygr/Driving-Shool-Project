package driving.school.repository;


import driving.school.model.RideDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepo extends JpaRepository<RideDate,Long> {
    List<RideDate> getAllByStudentId(Long id);
    List<RideDate> getAllByTeacherId(Long id);
}
