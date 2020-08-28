package driving.school.configuration;

import driving.school.model.user.*;
import driving.school.repository.AuthorityRepo;
import driving.school.repository.StudentRepo;
import driving.school.repository.TeacherRepo;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class Init {
    private final PasswordEncoder encoder;
    @Bean
    @Profile("!prod")
    public ApplicationRunner initializer(AuthorityRepo authorityRepository,
                                         TeacherRepo teacherRepo,
                                         StudentRepo studentRepo,
                                         UserRepo userRepo) {
        return args -> {
            authorityRepository.saveAll(
                    Arrays.stream(Role.values())
                            .map(x -> Authority.builder().name(x).build())
                            .collect(Collectors.toList())
            );
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
            teacherRepo.save(Teacher.builder()
                    .firstName("tea")
                    .lastName("cher")
                    .user(User.builder()
                            .username("teacher")
                            .password(encoder.encode("teacher"))
                            .roles(Set.of(Authority.builder().name(Role.TEACHER).build()))
                            .build())
                    .build());
            userRepo.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .roles(Set.of(Authority.builder().name(Role.ADMIN).build()))
                    .build());
        };
    }

}
