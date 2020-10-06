package driving.school.mapper;

import driving.school.dto.AvailableDateDto;
import driving.school.model.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvailableMapper {

    @Mapping(source = "teacherId", target = "teacher.id")
    AvailableDate map(AvailableDateDto availableDateDto);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.lastName", target = "teacherLastName")
    @Mapping(source = "teacher.firstName", target = "teacherFirstName")
    AvailableDateDto map(AvailableDate availableDate);
}
