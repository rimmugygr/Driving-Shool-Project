package driving.school.security;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("application.jwt")
@Data
@Setter
@Getter
@NoArgsConstructor
public class JwtConfig {
    private  String jwtSecret;
    private  String jwtExpirationMs;
}
