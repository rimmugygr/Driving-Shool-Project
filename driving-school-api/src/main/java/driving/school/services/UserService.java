package driving.school.services;

import driving.school.dto.JwtResponse;
import driving.school.dto.LoginRequest;
import driving.school.dto.SignupRequest;
import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import driving.school.model.user.User;
import driving.school.repository.AuthorityRepo;
import driving.school.repository.UserRepo;
import driving.school.security.components.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;
    private final AuthorityRepo authorityRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public boolean isUniqueUsername(User user) {
        User userResult = userRepository.findUserByUsername(user.getUsername());
        return userResult == null;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void createNewUser(SignupRequest signUpRequest) {

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();
        Set<Authority> roles = getAuthoritiesOrThrow(signUpRequest.getRole());
        user.setRoles(roles);
        userRepository.save(user);
    }

    private Set<Authority> getAuthoritiesOrThrow(Set<String> strRoles) {
        Set<Authority> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Authority defaultRole = authorityRepository.findByName(Role.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(defaultRole);
        } else {
            strRoles.forEach(role -> {
                Authority authority = authorityRepository.findByName(Role.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(authority);
            });
        }
        return roles;
    }

    public JwtResponse loginToGetJwt(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        User user = userRepository.findUserByUsername(userDetails.getUsername());
        return new JwtResponse(jwt,
                user.getId(),
                userDetails.getUsername(),
                authorities);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
