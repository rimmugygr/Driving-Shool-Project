package driving.school.services;


import driving.school.model.user.Teacher;
import driving.school.repository.TeacherRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherService {
    TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(String id) throws  NoSuchElementException{
        return teacherRepo.findById(Long.valueOf(id))
                .orElseThrow(() ->new NoSuchElementException("#Errror# Teacher on Id '"+ id +"' not exist in data base"));
    }

    public Teacher addTeacher(Teacher teacher) throws SQLIntegrityConstraintViolationException {
        return teacherRepo.save(teacher);
    }

    public void editTeacherById(String id, Teacher teacher) throws NoSuchElementException, SQLIntegrityConstraintViolationException {
        if (teacherRepo.existsById(Long.valueOf(id))) {
            teacherRepo.save(teacher);
        } else throw new NoSuchElementException("#Errror# Teacher on Id '"+ id +"' not exist in data base");
    }

    public void deleteTeacherById(String id) {
        teacherRepo.deleteById(Long.valueOf(id));
    }
}
