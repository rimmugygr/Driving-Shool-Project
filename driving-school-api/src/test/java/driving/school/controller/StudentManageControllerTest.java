package driving.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import driving.school.mapper.StudentMapper;
import driving.school.model.StudentStatus;
import driving.school.model.user.Student;
import driving.school.model.user.User;
import driving.school.services.StudentService;
import org.junit.jupiter.api.Test;
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
    ObjectMapper studentMapper;

    @MockBean
    StudentService mockService;

    @MockBean
    StudentMapper mockMapper;

    @Test
    void shouldAddStudentMenageWhenDataPost() throws Exception {
        //given
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
        String addRequestStudentJson = studentMapper.writeValueAsString(anyPostedStudent);
//        System.out.println(anyPostedStudent.toString());
//        System.out.println(addRequestStudentJson);
//        Mockito.doReturn(1L).when(mockService).addStudent(anyPostedStudent);
        //when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/api/manage/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addRequestStudentJson));

        //then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
//        result.andExpect(MockMvcResultMatchers.header().string("Location","api/manage/students/1"));
//        Mockito.verify(mockService).addStudent(anyPostedStudent);
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

        String editRequestCountryJson = studentMapper.writeValueAsString(anyPostedStudent);

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
