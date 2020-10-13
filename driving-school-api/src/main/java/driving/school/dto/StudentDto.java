package driving.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import driving.school.model.Category;
import driving.school.model.RideDate;
import driving.school.model.StudentStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Integer hours;
    private String phone;
    private StudentStatus status;
    private List<RideDateDto> rideDateList;
    private Set<Category> categoryList;
    private UserDto user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}
