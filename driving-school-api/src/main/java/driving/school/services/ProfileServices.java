package driving.school.services;

import driving.school.dto.request.LoginRequest;
import driving.school.dto.response.JwtResponse;
import driving.school.dto.response.ProfileDto;
import driving.school.mapper.StudentMapper;
import driving.school.mapper.TeacherMapper;
import driving.school.model.user.Role;
import driving.school.model.user.Student;
import driving.school.model.user.Teacher;
import driving.school.model.user.User;
import driving.school.security.components.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProfileServices {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public ProfileDto getProfile(String username) {
        User user = userService.getUser(username);
        String profileType = Role.ADMIN.name();
        if (user.getTeacher() != null) {
            profileType = Role.TEACHER.name();
        } else if (user.getStudent() != null) {
            profileType = Role.STUDENT.name();
        }
        return ProfileDto.builder()
                .id(user.getId())
                .username(username)
                .password(user.getPassword())
                .type(profileType)
                .build();
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

        User user = userService.getUser(userDetails.getUsername());
        return new JwtResponse(jwt,
                user.getId(),
                userDetails.getUsername(),
                authorities);
    }
}
