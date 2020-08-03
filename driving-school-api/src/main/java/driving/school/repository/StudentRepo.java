package driving.school.repository;


import driving.school.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
    @Override
    void deleteById(Long id);
}
