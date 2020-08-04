package driving.school.mapper;

import driving.school.dto.TeacherUserDto;
import driving.school.model.user.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    TeacherUserDto map(Teacher teacher);
    Teacher map(TeacherUserDto teacherUserDto);
}
