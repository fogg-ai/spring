package org.itstep.data;

import org.itstep.domain.Group;
import org.itstep.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

import org.itstep.domain.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.GroupService;
import org.itstep.service.StudentService;
import org.itstep.service.dto.GroupDto;
import org.itstep.service.dto.StudentDto;
import org.itstep.service.impl.StudentServiceImpl;
import org.itstep.service.mapper.GroupMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:spring-jdbc.xml")
@ActiveProfiles("dev")
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class JpaTest {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getTeachers() {
        assertNotNull(groupRepository);
        Teacher one = teacherRepository.getOne(1);
        assertEquals("Василий", one.getFirstName());
        List<Group> groups = one.getGroups();
        assertNotNull(groups);

    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getGroups() {
        assertNotNull(groupRepository);
        Group one = groupRepository.getOne(1);
        List<Student> students = one.getStudents();
        assertNotNull(students);
        List<Teacher> teachers = one.getTeachers();
        assertNotNull(teachers);
    }
}
