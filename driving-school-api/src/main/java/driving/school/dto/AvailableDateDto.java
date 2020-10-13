package driving.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateDto {
    private long id;
    private long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private boolean reserved;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

//    @Enumerated(EnumType.STRING)
//    private Category category;
}
