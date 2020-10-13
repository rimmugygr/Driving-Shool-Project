package driving.school.mapper;

import driving.school.dto.StudentDto;
import driving.school.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto map(Student student);

    Student map(StudentDto studentDto);
}
