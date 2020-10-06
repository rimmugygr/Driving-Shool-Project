package driving.school.controller.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileResponse {
    private Long id;
    private String username;
    private String type;
}
