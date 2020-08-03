package driving.school.model.user;


import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@SQLDelete(sql = "update student set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted <> true")
public class Student extends BaseEntity {
    @JsonView(Views.UserView.class)
    private String firstName;

    @JsonView(Views.UserView.class)
    private String lastName;

    @JsonView(Views.UserView.class)
    private String address;

    @JsonView(Views.UserView.class)
    private String email;

    @JsonView(Views.UserView.class)
    private Long hours;

    @JsonView(Views.UserView.class)
    private String phone;

    @Enumerated(EnumType.STRING)
    @JsonView(Views.UserView.class)
    private StudentStatus status;

    @JsonView(Views.UserView.class)
    @OneToMany(mappedBy = "student")
    private List<RideDate> rideDateList;

    @JsonView(Views.UserView.class)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Category> categoryList;

    @JsonView(Views.AdminView.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Student() {
    }

    public Student(Long id) {
        super(id);
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public List<RideDate> getRideDateList() {
        return rideDateList;
    }

    public void setRideDateList(List<RideDate> rideDateList) {
        this.rideDateList = rideDateList;
    }

    public Set<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
