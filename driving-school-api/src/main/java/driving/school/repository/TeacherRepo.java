package driving.school.repository;


import driving.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
    Teacher getByUserId(Long userId);
}
