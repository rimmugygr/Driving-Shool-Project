package driving.school.model.user;

import driving.school.model.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update teacher set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted <> true")
public class Teacher extends BaseEntity {
    private String firstName;
    private String lastName;
    private String address;

    @OneToMany(mappedBy = "teacher")
    private List<RideDate> rideDates;

    @OneToMany(mappedBy = "teacher")
    private List<AvailableDate> availableDates;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Category> categoryList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Teacher(Long id) {
        super(id);
    }
}
