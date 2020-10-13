package driving.school.services;

import driving.school.dto.StudentDto;
import driving.school.dto.UserDto;
import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import driving.school.mapper.StudentMapper;
import driving.school.model.Student;
import driving.school.model.user.Authority;
import driving.school.model.user.Role;
import driving.school.model.user.User;
import driving.school.repository.StudentRepo;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Import(StudentService.class)
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @Autowired
    StudentService studentService;
    @MockBean
    StudentRepo studentRepoMock;
    @MockBean
    UserService userServiceMock;
    @MockBean
    StudentMapper studentMapperMock;

    @Nested
    class GetAllStudent{
        @Test
        void shouldGetAllStudents() {
            //given
            List<Student> anyStudentList= new ArrayList<>();
            Student anyStudent1 = Student.builder()
                    .id(1L)
                    .build();
            Student anyStudent2 = Student.builder()
                    .id(2L)
                    .build();
            anyStudentList.add(anyStudent1);
            anyStudentList.add(anyStudent2);
            Mockito.when(studentRepoMock.findAll())
                    .thenReturn(anyStudentList);
            //when
            List<StudentDto> resultStudents = studentService.getAllStudent();
            //then
            MatcherAssert.assertThat(resultStudents, Matchers.notNullValue());
            MatcherAssert.assertThat(resultStudents.size(), Matchers.is(anyStudentList.size()));
        }
    }

    @Nested
    class GetStudentById{
        @Test
        void shouldGetStudentByIdWhenExist() {
            //given
            long studentId = 1;
            Student anyStudent = Student.builder()
                    .id(studentId)
                    .build();
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.of(anyStudent));
            //when
            StudentDto resultStudent = studentService.getStudentById(studentId);
            //then
            MatcherAssert.assertThat(resultStudent, Matchers.notNullValue());
            MatcherAssert.assertThat(resultStudent.getId(), Matchers.is(studentId));
        }

        @Test
        void shouldThrowExceptionWhenStudentByIdNotExist() {
            //given
            long studentId = 1;
            Student anyStudent = Student.builder()
                    .id(studentId)
                    .build();
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.empty());
            Exception caughtException = null;
            //when
            try { studentService.getStudentById(studentId); }
            catch (Exception e) { caughtException = e; }
            //then
            MatcherAssert.assertThat(caughtException, Matchers.notNullValue());
            MatcherAssert.assertThat(caughtException, Matchers.instanceOf(ResourcesNotFound.class));
            MatcherAssert.assertThat(caughtException.getMessage(), Matchers.is("Student on Id '" + studentId + "' not exist"));
        }
    }
    @Nested
    class AddStudent{
        @Test
        void shouldReturnStudent() {
            //given
            StudentDto anyStudentDto = StudentDto.builder()
                    .firstName("aaa")
                    .build();
            StudentDto resultStudentDto = StudentDto.builder()
                    .id(1L)
                    .firstName("aaa")
                    .build();
            Student anyStudent = Student.builder()
                    .firstName("aaa")
                    .build();
            Student resultStudent = Student.builder()
                    .id(1L)
                    .firstName("aaa")
                    .build();
            Mockito.when(studentRepoMock.save(anyStudent))
                    .thenReturn(resultStudent);
            Mockito.when(studentMapperMock.map(anyStudentDto))
                    .thenReturn(anyStudent);
            Mockito.when(studentMapperMock.map(anyStudent))
                    .thenReturn(anyStudentDto);
            //when
            StudentDto result = studentService.addStudent(anyStudentDto);
            //then
            MatcherAssert.assertThat(resultStudent, Matchers.notNullValue());
            MatcherAssert.assertThat(resultStudent, Matchers.is(resultStudent));
        }
        @Test
        void shouldAddStudentWithUser() {
            //given
            UserDto anyUserDto = UserDto.builder()
                    .username("xx")
                    .password("yy")
                    .build();
            User anyUser = User.builder()
                    .username("xx")
                    .password("yy")
                    .roles(Set.of(Authority.builder().name(Role.STUDENT).build()))
                    .build();
            Student anyStudent = Student.builder()
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            StudentDto anyStudentDto = StudentDto.builder()
                    .firstName("aaa")
                    .user(anyUserDto)
                    .build();
            Student resultStudent = Student.builder()
                    .id(1L)
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            Mockito.when(studentRepoMock.save(anyStudent))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUser.getUsername()))
                    .thenReturn(true);
            Mockito.when(studentMapperMock.map(anyStudentDto))
                    .thenReturn(anyStudent);
            Mockito.when(studentMapperMock.map(anyStudent))
                    .thenReturn(anyStudentDto);
            //when
            studentService.addStudent(anyStudentDto);
            //then
            Mockito.verify(studentRepoMock).save(anyStudent);
        }
//        @Test
//        void shouldAddStudentWithoutUser() {
//            //given
//            Student anyStudent = Student.builder()
//                    .firstName("aaa")
//                    .lastName("bbb")
//                    .build();
//            Student resultStudent = Student.builder()
//                    .id(1L)
//                    .firstName("aaa")
//                    .lastName("bbb")
//                    .build();
//            Mockito.when(studentRepoMock.save(anyStudent))
//                    .thenReturn(resultStudent);
//            //when
//            studentService.addStudent(anyStudent);
//            //then
//            Mockito.verify(studentRepoMock).save(anyStudent);
//        }
        @Test
        void shouldThrowExceptionWhenUsernameNotUnique() {
            //given
            String username = "std";
            User anyUser = User.builder().username(username).build();
            UserDto anyUserDto = UserDto.builder().username(username).build();
            Student anyStudent = Student.builder()
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            StudentDto anyStudentDto = StudentDto.builder()
                    .firstName("aaa")
                    .user(anyUserDto)
                    .build();
            Student resultStudent = Student.builder()
                    .id(1L)
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            Exception caughtException = null;
            Mockito.when(studentRepoMock.save(anyStudent))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUser.getUsername()))
                    .thenReturn(false);
            Mockito.when(studentMapperMock.map(anyStudentDto))
                    .thenReturn(anyStudent);
            Mockito.when(studentMapperMock.map(anyStudent))
                    .thenReturn(anyStudentDto);
            //when
            try { studentService.addStudent(anyStudentDto); }
            catch (Exception e) { caughtException = e; }
            //then
            MatcherAssert.assertThat(caughtException, Matchers.notNullValue());
            MatcherAssert.assertThat(caughtException, Matchers.instanceOf(DuplicateUniqueKey.class));
            MatcherAssert.assertThat(caughtException.getMessage(), Matchers.is("Username '" + username + "' already exist"));
        }
    }

    @Nested
    class DeleteStudentById{
        @Test
        void shouldDeleteStudentById() {
            //given
            long studentId = 1;
            //when
            studentService.deleteStudentById(studentId);
            //then
            Mockito.verify(studentRepoMock).deleteById(studentId);
        }

    }

    @Nested
    class PutStudentById{
        @Test
        void shouldThrowExceptionWhenUsernameNotUnique() {
            //given
            String username = "notUniqueUsername";
            long studentId = 1L;
            User anyUser = User.builder().username(username).build();
            UserDto anyUserDto = UserDto.builder().username(username).build();
            Student anyStudentInput = Student.builder()
                    .user(anyUser)
                    .build();
            StudentDto anyStudentInputDto = StudentDto.builder()
                    .user(anyUserDto)
                    .build();
            Student anyStudentFromDB = Student.builder()
                    .id(studentId)
                    .build();
            Student resultStudent = Student.builder()
                    .id(studentId)
                    .user(anyUser)
                    .build();
            Exception caughtException = null;
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.of(anyStudentFromDB));
            Mockito.when(studentRepoMock.save(anyStudentInput))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUser.getUsername()))
                    .thenReturn(false);
            //when
            try { studentService.putStudentById(studentId,anyStudentInputDto); }
            catch (Exception e) { caughtException = e; }
            //then
            MatcherAssert.assertThat(caughtException, Matchers.notNullValue());
            MatcherAssert.assertThat(caughtException, Matchers.instanceOf(DuplicateUniqueKey.class));
            MatcherAssert.assertThat(caughtException.getMessage(), Matchers.is("Username '" + username + "' already exist"));
        }
        @Test
        void shouldThrowExceptionWheStudentNotExist() {
            //given
            String username = "uniqueUsername";
            long studentId = 1L;
            User anyUser = User.builder().username(username).build();
            UserDto anyUserDto = UserDto.builder().username(username).build();
            Student anyStudentInput = Student.builder()
                    .user(anyUser)
                    .build();
            StudentDto anyStudentInputDto = StudentDto.builder()
                    .user(anyUserDto)
                    .build();
            Student resultStudent = Student.builder()
                    .id(studentId)
                    .user(anyUser)
                    .build();
            Exception caughtException = null;
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.empty());
            Mockito.when(studentRepoMock.save(anyStudentInput))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUser.getUsername()))
                    .thenReturn(true);
            //when
            try { studentService.putStudentById(studentId,anyStudentInputDto); }
            catch (Exception e) { caughtException = e; }
            //then
            MatcherAssert.assertThat(caughtException, Matchers.notNullValue());
            MatcherAssert.assertThat(caughtException, Matchers.instanceOf(ResourcesNotFound.class));
            MatcherAssert.assertThat(caughtException.getMessage(), Matchers.is("Student on Id '" + studentId + "' not exist"));
        }
        @Test
        void shouldPutDataWhenCorrectAndNotChangePassword() {
            //given
            long studentId = 1L;
            long userId = 1L;
            User anyUser = User.builder().id(userId).username("uniqueUsername").build();
            UserDto anyUserDto = UserDto.builder().id(userId).username("uniqueUsername").build();
            Student anyStudentInput = Student.builder()
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            StudentDto anyStudentInputDto = StudentDto.builder()
                    .firstName("aaa")
                    .user(anyUserDto)
                    .build();
            Student anyStudentFromDB = Student.builder()
                    .id(studentId)
                    .lastName("aaa")
                    .user(anyUser)
                    .build();
            Student resultStudent = Student.builder()
                    .id(studentId)
                    .firstName("aaa")
                    .user(anyUser)
                    .build();
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.of(anyStudentFromDB));
            Mockito.when(studentRepoMock.save(anyStudentInput))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUser.getUsername()))
                    .thenReturn(true);
            //when
            studentService.putStudentById(studentId,anyStudentInputDto);
            //then
            Mockito.verify(studentRepoMock).save(resultStudent);
        }
        @Test
        void shouldPutDataWhenCorrectAndChangePassword() {
            //given
            long studentId = 2L;
            long userId = 2L;
            User anyUserInput = User.builder().id(userId).username("uniqueUsername").build();
            UserDto anyUserDto = UserDto.builder().id(userId).username("uniqueUsername").build();
            Student anyStudentInput = Student.builder()
                    .firstName("aaa")
                    .user(anyUserInput)
                    .build();
            StudentDto anyStudentInputDto = StudentDto.builder()
                    .firstName("aaa")
                    .user(anyUserDto)
                    .build();

            User anyUserFromDB = User.builder().id(userId).username("anotherUniqueUsername").build();
            Student anyStudentFromDB = Student.builder()
                    .id(studentId)
                    .lastName("aaa")
                    .user(anyUserFromDB)
                    .build();
            Student resultStudent = Student.builder()
                    .id(studentId)
                    .firstName("aaa")
                    .user(anyUserInput)
                    .build();
            Mockito.when(studentRepoMock.findById(studentId))
                    .thenReturn(Optional.of(anyStudentFromDB));
            Mockito.when(studentRepoMock.save(anyStudentInput))
                    .thenReturn(resultStudent);
            Mockito.when(userServiceMock.isUniqueUsername(anyUserInput.getUsername()))
                    .thenReturn(true);
            //when
            studentService.putStudentById(studentId,anyStudentInputDto);
            //then
            Mockito.verify(studentRepoMock).save(resultStudent);
        }
    }


}
