package driving.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import driving.school.model.RideStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RideDateDto{
    private Long id;
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
