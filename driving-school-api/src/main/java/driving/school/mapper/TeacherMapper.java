package driving.school.mapper;

import driving.school.dto.TeacherUserDto;
import driving.school.model.user.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherUserDto map(Teacher teacher);

    Teacher map(TeacherUserDto teacherUserDto);
}
