package driving.school.services;

import driving.school.controller.request.LoginRequest;
import driving.school.controller.request.ProfileRequest;
import driving.school.controller.response.LoginResponse;
import driving.school.controller.response.ProfileResponse;
import driving.school.dto.StudentDto;
import driving.school.dto.TeacherDto;
import driving.school.model.user.Role;
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
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public ProfileResponse<?> getProfile(String username) {
        User user = userService.getUser(username);
        Object profile = null;
        if (user.getType().equals(Role.TEACHER)) {
            profile = teacherService.getTeacherById(user.getId());
        } else if (user.getType().equals(Role.STUDENT)) {
            profile = studentService.getStudentByUserId(user.getId());
        }
        return ProfileResponse.builder()
                .profile(profile)
                .build();
    }

    public LoginResponse loginToGetJwtAndProfile(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ProfileResponse<?> profile = getProfile(userDetails.getUsername());
        User user = userService.getUser(userDetails.getUsername());

        return LoginResponse.builder()
                .userId(user.getId())
                .token(jwt)
                .username(userDetails.getUsername())
                .roles(authorities)
                .profile(profile)
                .build();
    }

    public ProfileResponse<?> updateProfile(String username, ProfileRequest<?> profileRequest) {
        Object profile = null;
         if (profileRequest.getProfile() instanceof TeacherDto) {
            TeacherDto teacherDto = (TeacherDto)profileRequest.getProfile();
            Long id = userService.getUser(username).getTeacher().getId();
            profile = teacherService.putTeacherById(id,teacherDto);
        } else if (profileRequest.getProfile() instanceof StudentDto) {
            StudentDto studentDto = (StudentDto)profileRequest.getProfile();
            Long id = userService.getUser(username).getStudent().getId();
            profile = studentService.putStudentById(id,studentDto);
        }

        return ProfileResponse.builder()
                .profile(profile)
                .build();
    }
}
