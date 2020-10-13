package driving.school.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "available_date", uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "teacher_id" } ) } )
public class AvailableDate extends BaseEntity{
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @Column(name = "reserved", columnDefinition = "BOOLEAN")
    private boolean reserved;

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
