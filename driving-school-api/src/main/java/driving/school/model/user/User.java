package driving.school.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import driving.school.model.BaseEntity;
import driving.school.model.Views;
import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class User extends BaseEntity {
    @JsonView(Views.AdminView.class)
    @Column(name = "login_name", unique = true, nullable = false)
    private String loginName;

    @JsonView(Views.AdminView.class)
    private String password;

    public User() {
    }

    public User(Long id) {
        super(id);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
