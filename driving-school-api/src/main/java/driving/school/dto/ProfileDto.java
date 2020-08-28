package driving.school.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileDto {
    private Long id;
    private String password;
    private String username;
    private String type;
}
