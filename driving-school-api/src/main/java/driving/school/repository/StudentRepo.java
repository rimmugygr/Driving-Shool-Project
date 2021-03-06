package driving.school.repository;


import driving.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
    Student getByUserId(Long id);

}
