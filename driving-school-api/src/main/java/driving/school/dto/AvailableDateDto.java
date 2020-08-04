package driving.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import driving.school.model.BaseEntity;
import driving.school.model.user.Teacher;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Teacher teacher;
    private boolean reserved;
//    @Enumerated(EnumType.STRING)
//    private Category category;
}
