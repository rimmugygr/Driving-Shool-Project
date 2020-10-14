package driving.school.services;


import driving.school.dto.TeacherDto;
import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.mapper.TeacherMapper;
import driving.school.model.Teacher;
import driving.school.model.user.*;
import driving.school.repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final TeacherMapper teacherMapper;

    public List<TeacherDto> getAllTeacher() {
        return teacherRepo.findAll().stream()
                .map(teacherMapper::map)
                .collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(Long id) {
        return teacherRepo.findById(id)
                .map(teacherMapper::map)
                .orElseThrow(() ->new ResourcesNotFound("Teacher on id '"+ id +"' not exist"));
    }

    public TeacherDto addTeacher(TeacherDto teacherDto){
        if(!userService.isUniqueUsername(teacherDto.getUser().getUsername()))
            throw new DuplicateUniqueKey("Username '" + teacherDto.getUser().getUsername() + "' already exist");
        Teacher teacher = teacherMapper.map(teacherDto);
        teacher.setUser(User.builder()
                .username(teacherDto.getUser().getUsername())
                .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                .password(encoder.encode(teacherDto.getUser().getPassword()))
                .build());
        return teacherMapper.map(teacherRepo.save(teacher));
    }

    public TeacherDto putTeacherById(Long teacherId, TeacherDto newTeacherDto) {
        TeacherDto oldTeacherDto = getTeacherById(teacherId);
        if (!oldTeacherDto.getUser().getUsername().equals(newTeacherDto.getUser().getUsername())) {
            if(!userService.isUniqueUsername(newTeacherDto.getUser().getUsername()))
                throw new DuplicateUniqueKey("Username '" + newTeacherDto.getUser().getUsername() + "' already exist");
        }
        User user ;
        if(newTeacherDto.getUser().getPassword().isEmpty()) {            // if no password then not change
            user = User.builder()
                    .username(newTeacherDto.getUser().getUsername())
                    .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                    .password(oldTeacherDto.getUser().getPassword())
                    .build();
        } else { // if password not null then encode this
            user = User.builder()
                    .username(newTeacherDto.getUser().getUsername())
                    .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                    .password(encoder.encode(newTeacherDto.getUser().getPassword()))
                    .build();
        }
        Teacher teacher = teacherMapper.map(newTeacherDto);
        teacher.setId(teacherId);
        teacher.setUser(user);
        return teacherMapper.map(teacherRepo.save(teacher));
    }

    public TeacherDto deleteTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourcesNotFound("Teacher on Id '" + id + "' not exist"));
        TeacherDto teacherDto = teacherMapper.map(teacher);
        userService.deleteUser(teacher.getUser());
        teacherRepo.deleteById(id);
        return teacherDto;
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepo.getByUserId(userId);
    }
}
