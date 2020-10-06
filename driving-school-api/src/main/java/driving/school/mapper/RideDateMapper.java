package driving.school.mapper;


import driving.school.dto.RideDateDto;
import driving.school.model.RideDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RideDateMapper {

    @Mapping(source = "teacherId", target = "teacher.id")
    @Mapping(source = "studentId", target = "student.id")
    RideDate map(RideDateDto rideDateDto);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.lastName", target = "teacherLastName")
    @Mapping(source = "teacher.firstName", target = "teacherFirstName")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.lastName", target = "studentLastName")
    @Mapping(source = "student.firstName", target = "studentFirstName")
    RideDateDto map(RideDate rideDate);
}
