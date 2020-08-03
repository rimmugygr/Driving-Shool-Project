package driving.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.user.Student;
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
@Table( name = "ride_date",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "student_id" } ) } )
public class RideDate extends BaseEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal( TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="student_id")

    @JsonUnwrapped(suffix = "Student")
    @JsonIgnoreProperties( {"address","rideDates","rideDateList","categoryList","hours","status","updateDate","createDate"})
    private Student student;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonUnwrapped(suffix = "Teacher")
    @JsonIgnoreProperties( {"address","rideDates","availableDates","categoryList","updateDate","createDate"})
    private Teacher teacher;


    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    public RideDate(Date date, Student student, Teacher teacher) {
        this.date = date;
        this.student = student;
        this.teacher = teacher;
    }

    //    @Enumerated(EnumType.STRING)
//    private Category category;
}
