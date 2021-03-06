package driving.school.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "ride_date", uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "student_id" } ) } )
public class RideDate extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Column(name = "reserved", columnDefinition = "BOOLEAN")
    private boolean reserved;

    public RideDate(LocalDateTime date, Student student, Teacher teacher) {
        this.date = date;
        this.student = student;
        this.teacher = teacher;
    }

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
