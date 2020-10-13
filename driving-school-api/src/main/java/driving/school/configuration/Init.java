package driving.school.configuration;

import driving.school.model.AvailableDate;
import driving.school.model.Student;
import driving.school.model.Teacher;
import driving.school.model.user.*;
import driving.school.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@AllArgsConstructor
public class Init {
    private final PasswordEncoder encoder;
    @Bean
    @Profile("!prod")
    public ApplicationRunner initializer(AuthorityRepo authorityRepository,
                                         TeacherRepo teacherRepo,
                                         StudentRepo studentRepo,
                                         UserRepo userRepo,
                                         AvailableDateRepo availableDateRepo) {
        return args -> {
            //roles
            authorityRepository.saveAll(
                    Arrays.stream(Role.values())
                            .map(x -> Authority.builder().name(x).build())
                            .collect(Collectors.toList())
            );
            //student profile example
            studentRepo.save(Student.builder()
                    .firstName("stud")
                    .lastName("end")
                    .email("a@b.pl")
                    .user(User.builder()
                            .username("student")
                            .password(encoder.encode("student"))
                            .roles(Set.of(Authority.builder().name(Role.STUDENT).build()))
                            .build())
                    .build());
            //teacher profile example
            Teacher teacher = Teacher.builder()
                    .firstName("tea")
                    .lastName("cher")
                    .user(User.builder()
                            .username("teacher")
                            .password(encoder.encode("teacher"))
                            .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                            .build())
                    .build();
            teacherRepo.save(teacher);
            availableDateRepo.save(AvailableDate.builder()
                    .teacher(teacher)
                    .date(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)))
                    .reserved(false)
                    .build());
            //admin user example
            userRepo.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .roles(Set.of(Authority.builder().name(Role.ADMIN).build()))
                    .build());
            //teacher with available date example
            Teacher teacher1 = Teacher.builder()
                    .firstName("teacher1")
                    .lastName("teacher1")
                    .user(User.builder()
                            .username("teacher1")
                            .password(encoder.encode("teacher1"))
                            .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                            .build())
                    .build();
            teacherRepo.save(teacher1);
            IntStream.of(8,10,12,14).forEach(hour ->
                    IntStream.of(19,20,21).forEach( day ->
                            availableDateRepo.save(AvailableDate.builder()
                                    .teacher(teacher1)
                                    .date(LocalDateTime.of(2020,10,day,hour,0))
                                    .reserved(false)
                                    .build())));
            //teacher with available date example
            Teacher teacher2 = Teacher.builder()
                    .firstName("teacher2")
                    .lastName("teacher2")
                    .user(User.builder()
                            .username("teacher2")
                            .password(encoder.encode("teacher2"))
                            .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                            .build())
                    .build();
            teacherRepo.save(teacher2);
            IntStream.of(10,12,14,16).forEach(hour ->
                    IntStream.of(20,21,22).forEach( day ->
                            availableDateRepo.save(AvailableDate.builder()
                                    .teacher(teacher2)
                                    .date(LocalDateTime.of(2020,10,day,hour,0))
                                    .reserved(false)
                                    .build())));
        };
    }

}
