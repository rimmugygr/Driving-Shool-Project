package driving.school.controller;


import driving.school.dto.ProfileDto;
import driving.school.security.CurrentUsername;
import driving.school.dto.response.JwtResponse;
import driving.school.dto.request.LoginRequest;
import driving.school.services.ProfileServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ProfileController {
    private final ProfileServices profileServices;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = profileServices.loginToGetJwt(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@CurrentUsername String username) {
        ProfileDto profile = profileServices.getProfile(username);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> putProfile(@CurrentUsername String username, ProfileDto profileDto) {
        profileServices.updateProfile(username, profileDto);
        return ResponseEntity.ok().build();
    }

}
