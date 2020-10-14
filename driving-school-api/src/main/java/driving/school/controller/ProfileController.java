package driving.school.controller;

import driving.school.controller.request.ProfileRequest;
import driving.school.controller.response.ProfileResponse;
import driving.school.security.CurrentUsername;
import driving.school.controller.response.LoginResponse;
import driving.school.controller.request.LoginRequest;
import driving.school.services.ProfileServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ProfileController {
    private final ProfileServices profileServices;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = profileServices.loginToGetJwtAndProfile(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ProfileResponse<?> getProfile(@CurrentUsername String username) {
        return profileServices.getProfile(username);
    }

    @PutMapping("/profile")
    public ProfileResponse<?> putProfile(@CurrentUsername String username,@RequestBody ProfileRequest<?> profileRequest) {
        return profileServices.updateProfile(username, profileRequest);
    }
}
