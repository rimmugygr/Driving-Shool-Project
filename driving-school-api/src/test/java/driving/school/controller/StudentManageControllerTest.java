package driving.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import driving.school.dto.StudentUserDto;
import driving.school.dto.UserDto;
import driving.school.mapper.StudentMapper;
import driving.school.model.StudentStatus;
import driving.school.model.user.Student;
import driving.school.model.user.User;
import driving.school.services.StudentService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = {StudentManageController.class})
class StudentManageControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    StudentService studentServiceMock;

    @MockBean
    StudentMapper studentMapperMock;

    long studentId;
    Student anyStudent;
    StudentUserDto anyStudentDto;
    String requestStudentJson;
    ResultActions result;

    @DisplayName("when data POST /post/runner/{id}")
    @Nested
    class  PostStudent{
        @BeforeEach
        void initEach() throws Exception {
            //given
            studentId = 1L;

            anyStudent = Student.builder()
                    .address("home")
                    .firstName("first")
                    .lastName("last")
                    .email("a@a.pl")
                    .hours(0)
                    .phone("12345")
                    .status(StudentStatus.Active)
                    .user(User.builder().username("00000").password("000000").build())
                    .build();

            anyStudentDto = StudentUserDto.builder()
                    .address("home")
                    .firstName("first")
                    .lastName("last")
                    .email("a@a.pl")
                    .hours(0)
                    .phone("12345")
                    .status(StudentStatus.Active)
                    .user(UserDto.builder().username("00000").build())
                    .build();

            requestStudentJson = mapper.writeValueAsString(anyStudent);

            Mockito.when(studentMapperMock.map(anyStudentDto))
                    .thenReturn(anyStudent);

            Mockito.when(studentServiceMock.addStudent(anyStudent))
                    .thenReturn(1L);
            //when
            result = mvc.perform(
                    MockMvcRequestBuilders.post("/api/manage/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestStudentJson));

        }
        @Test
        void shouldAddStudent() throws Exception {
            //then
            Mockito.verify(studentServiceMock).addStudent(anyStudent);
        }
        @Test
        void shouldReturnInHeaderLocationResources() throws Exception {
            //then
            result.andExpect(MockMvcResultMatchers.header().string("Location","api/manage/students/" + studentId));
        }
        @Test
        void shouldReturnIsCreated() throws Exception {
            //then
            result.andExpect(MockMvcResultMatchers.status().isCreated());
        }
        @AfterEach
        void reset() {
            Mockito.reset(studentServiceMock,studentMapperMock);
        }
    }

    @DisplayName("when data PUT /post/runner/{id}")
    @Nested
    class  PutStudent{
        @BeforeEach
        void initEach() throws Exception {
            //given
            studentId = 1L;

            anyStudent = Student.builder()
                    .address("home")
                    .firstName("first")
                    .lastName("last")
                    .email("a@a.pl")
                    .hours(0)
                    .phone("12345")
                    .status(StudentStatus.Active)
                    .user(User.builder().username("00000").password("000000").build())
                    .build();

            anyStudentDto = StudentUserDto.builder()
                    .address("home")
                    .firstName("first")
                    .lastName("last")
                    .email("a@a.pl")
                    .hours(0)
                    .phone("12345")
                    .status(StudentStatus.Active)
                    .user(UserDto.builder().username("00000").build())
                    .build();

            requestStudentJson = mapper.writeValueAsString(anyStudent);

            Mockito.when(studentMapperMock.map(anyStudentDto))
                    .thenReturn(anyStudent);

            //when
            result = mvc.perform(
                    MockMvcRequestBuilders.put("/api/manage/students/" + studentId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestStudentJson));

        }
        @Test
        void shouldPutStudentById() throws Exception {
            //then
            Mockito.verify(studentServiceMock).putStudentById(studentId, anyStudent);
        }
        @Test
        void shouldReturnIsOk() throws Exception {
            //then
            result.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @AfterEach
        void reset() {
            Mockito.reset(studentServiceMock,studentMapperMock);
        }
    }
    @Test
    void shouldPatchStudentMenageWhenDataPatch() throws Exception {
        //given
        long countryId = 3;

        Student anyPostedStudent = Student.builder()
                .address("home")
                .firstName("first")
                .lastName("last")
                .email("a@a.pl")
                .hours(0)
                .phone("12345")
                .status(StudentStatus.Active)
                .user(User.builder().username("00000").password("000000").build())
                .id(null)
                .updateDate(null)
                .createDate(null)
                .build();

        String editRequestCountryJson = mapper.writeValueAsString(anyPostedStudent);

        //when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.put("/api/manage/students/{id}", countryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(editRequestCountryJson));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
       // Mockito.verify(mockService).editStudentById(3,anyPostedStudent);
    }
}
