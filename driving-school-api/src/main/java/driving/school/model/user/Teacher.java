package driving.school.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@SQLDelete(sql = "update teacher set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted <> true")
public class Teacher extends BaseEntity {
    @JsonView(Views.UserView.class)
    private String firstName;

    @JsonView(Views.UserView.class)
    private String lastName;

    @JsonView(Views.UserView.class)
    private String address;

    @JsonView(Views.UserView.class)
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<RideDate> rideDates;

    @JsonView(Views.UserView.class)
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<AvailableDate> availableDates;

    @JsonView(Views.UserView.class)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Category> categoryList;

    @JsonView(Views.AdminView.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Teacher() {
    }

    public Teacher(Long id) {
        super(id);
    }

    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<AvailableDate> availableDates) {
        this.availableDates = availableDates;
    }

    public List<RideDate> getRideDates() {
        return rideDates;
    }

    public void setRideDates(List<RideDate> rideDateList) {
        this.rideDates = rideDateList;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
