package driving.school.dto.request;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileRequest {
    private Long id;
    private String username;
    private String password;
}
