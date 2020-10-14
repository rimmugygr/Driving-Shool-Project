package driving.school.controller.request;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileRequest<Profile> {
    private Profile profile;
}
