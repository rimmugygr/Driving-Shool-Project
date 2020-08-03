package driving.school.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update school set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted <> true")
public class School extends BaseEntity {
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
