package driving.school.mapper;

import driving.school.dto.TeacherDto;
import driving.school.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto map(Teacher teacher);

    Teacher map(TeacherDto teacherDto);
}
