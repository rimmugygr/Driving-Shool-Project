package driving.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import driving.school.model.user.Teacher;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "available_date", uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "teacher_id" } ) } )
public class AvailableDate extends BaseEntity{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal( TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @Column(name = "reserved", columnDefinition = "BOOLEAN")
    private boolean reserved;

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
