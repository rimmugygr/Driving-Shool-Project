package driving.school.configuration;

import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import driving.school.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class Init {

    @Bean
    public ApplicationRunner initializer(AuthorityRepository repository) {
        return args -> repository.saveAll(
                Arrays.stream(Role.values())
                        .map(x->Authority.builder().name(x).build())
                        .collect(Collectors.toList())
        );
    }

}
