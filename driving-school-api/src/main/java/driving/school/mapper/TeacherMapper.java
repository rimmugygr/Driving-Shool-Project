package driving.school.mapper;

import driving.school.dto.StudentUserDto;
import driving.school.model.user.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    StudentUserDto map(Student student);
    Student map(StudentUserDto studentUserDto);
}
