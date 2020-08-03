package driving.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.user.Student;
import driving.school.model.user.Teacher;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "ride_date",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "date", "student_id" } ) } )
public class RideDate extends BaseEntity {
    @JsonView(Views.UserView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal( TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="student_id")
    @JsonView(Views.UserView.class)
    @JsonUnwrapped(suffix = "Student")
    @JsonIgnoreProperties( {"address","rideDates","rideDateList","categoryList","hours","status","updateDate","createDate"})
    private Student student;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonView(Views.UserView.class)
    @JsonUnwrapped(suffix = "Teacher")
    @JsonIgnoreProperties( {"address","rideDates","availableDates","categoryList","updateDate","createDate"})
    private Teacher teacher;

    @JsonView(Views.UserView.class)
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

//    @Enumerated(EnumType.STRING)
//    private Category category;

    public RideDate() {
    }

    public RideDate(Date date, Student student, Teacher teacher) {
        this.date = date;
        this.student = student;
        this.teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
