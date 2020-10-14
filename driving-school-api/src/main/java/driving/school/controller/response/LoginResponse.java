package driving.school.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String token;
	private String type = "Bearer";
	private Long userId;
	private String username;
	private List<String> roles;
	private ProfileResponse<?> profile;
}
