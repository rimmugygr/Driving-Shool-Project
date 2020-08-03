package driving.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import driving.school.model.user.Teacher;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "available_date",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "teacher_id" } ) } )
//@Where(clause = "date > CURRENT_TIMESTAMP()")
public class AvailableDate extends BaseEntity{
    @JsonView(Views.UserView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal( TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonView(Views.UserView.class)
    @JsonUnwrapped(suffix = "Teacher")
    @JsonIgnoreProperties( {"address","rideDates","availableDates","categoryList","updateDate","createDate"})
    private Teacher teacher;

//    @Enumerated(EnumType.STRING)
//    private Category category;

    @JsonView(Views.UserView.class)
    @Column(name = "reserved", columnDefinition = "BOOLEAN")
    @NotNull
    private boolean reserved = false;

    public AvailableDate() {
    }

    public AvailableDate(Long id) {
        super(id);
    }

    public AvailableDate(Date date, Teacher teacher) {
        this.date = date;
        this.teacher = teacher;
    }



    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
