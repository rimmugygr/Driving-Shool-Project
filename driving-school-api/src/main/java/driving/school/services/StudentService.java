package driving.school.services;

import driving.school.dto.StudentDto;
import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.mapper.StudentMapper;
import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import driving.school.model.Student;
import driving.school.model.user.User;
import driving.school.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final StudentMapper studentMapper;

    public List<StudentDto> getAllStudent() {
        return studentRepo.findAll().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(long id)  {
        return studentRepo.findById(id)
                .map(studentMapper::map)
                .orElseThrow(() ->new ResourcesNotFound("Student on Id '"+ id +"' not exist"));
    }

    public StudentDto addStudent(StudentDto studentDto)  {
        isUniqueUsername(studentDto);
        Student student = studentMapper.map(studentDto);
        student.setUser(User.builder()
                .username(studentDto.getUser().getUsername())
                .roles(Set.of(Authority.builder().name(Role.STUDENT).build()))
                .password(encoder.encode(studentDto.getUser().getPassword()))
                .build());
        return  studentMapper.map(studentRepo.save(student));
    }

    public StudentDto putStudentById(long id, StudentDto newStudentDto) {
        StudentDto oldStudent = getStudentById(id);
        isValidUsername(newStudentDto, oldStudent);

        Student student = studentMapper.map(newStudentDto);
        User user;
        if (newStudentDto.getUser().getPassword().isEmpty()) {            // if no password then not change
            user = User.builder()
                    .username(newStudentDto.getUser().getUsername())
                    .roles(Set.of(Authority.builder().name(Role.STUDENT).build()))
                    .password(oldStudent.getUser().getPassword())
                    .build();
        } else {            // if password then encode
            user = User.builder()
                    .username(newStudentDto.getUser().getUsername())
                    .roles(Set.of(Authority.builder().name(Role.STUDENT).build()))
                    .password(encoder.encode(newStudentDto.getUser().getPassword()))
                    .build();
        }
        student.setId(id);
        student.setUser(user);
        return studentMapper.map(studentRepo.save(student));
    }

    public StudentDto deleteStudentById(long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFound("Student on Id '" + id + "' not exist"));
        StudentDto studentDto = studentMapper.map(student);
        userService.deleteUser(student.getUser());
        studentRepo.deleteById(id);
        return studentDto;
    }

    private void isValidUsername(StudentDto newStudent, StudentDto oldStudent) {
        if (isHaveUsername(oldStudent)){
            isUniqueUsername(newStudent);
        } else if (newStudent.getUser() != null){
            if (isDifferentUsername(newStudent, oldStudent)) {
                isUniqueUsername(newStudent);
            }
        }
    }

    private boolean isDifferentUsername(StudentDto newStudent, StudentDto oldStudent) {
        return !oldStudent.getUser().getUsername().equals(newStudent.getUser().getUsername());
    }

    private boolean isHaveUsername(StudentDto oldStudent) {
        return oldStudent.getUser() == null || oldStudent.getUser().getUsername() == null;
    }

    private void isUniqueUsername(StudentDto student) {
        if (student.getUser() != null) {
            if(!userService.isUniqueUsername(student.getUser().getUsername()))
                throw new DuplicateUniqueKey("Username '" + student.getUser().getUsername() + "' already exist");
        }
    }

    public Student getStudentByUserId(Long userId) {
        return studentRepo.getByUserId(userId);
    }
}
