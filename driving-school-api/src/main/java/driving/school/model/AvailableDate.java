package driving.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
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
@Table( name = "available_date",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "teacher_id" } ) } )
//@Where(clause = "date > CURRENT_TIMESTAMP()")
public class AvailableDate extends BaseEntity{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal( TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonUnwrapped(suffix = "Teacher")
    @JsonIgnoreProperties( {"address","rideDates","availableDates","categoryList","updateDate","createDate"})
    private Teacher teacher;

//    @Enumerated(EnumType.STRING)
//    private Category category;

    @Column(name = "reserved", columnDefinition = "BOOLEAN")
    @NotNull
    private boolean reserved;
}
