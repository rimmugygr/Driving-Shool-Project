package driving.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import driving.school.model.AvailableDate;
import driving.school.model.Category;
import driving.school.model.RideDate;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TeacherUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<AvailableDate> availableDates;
    private List<RideDate> rideDateList;
    private Set<Category> categoryList;
    private UserDto user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
