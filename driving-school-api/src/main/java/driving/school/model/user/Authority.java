package driving.school.model.user;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Authority {
    @Id
    @Enumerated(EnumType.STRING)
    private Role name;
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userAuthorities")
//    private Collection<User> users;
}
