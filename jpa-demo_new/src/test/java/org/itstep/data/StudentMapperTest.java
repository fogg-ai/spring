package org.itstep.data;

import org.itstep.service.dto.StudentDto;
import org.itstep.domain.Group;
import org.itstep.domain.Student;
import org.itstep.service.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class StudentMapperTest {

    @Autowired
    StudentMapper mapper;

    @Test
    void toEntity() {
        StudentDto studentDto = new StudentDto(1, "Вася", "Пупкин",
                LocalDate.of(2000, 1, 1), 1, "Java");
        Student student = mapper.toEntity(studentDto);
        assertNotNull(student);
        assertEquals("Вася", student.getFirstName());
        assertEquals("Пупкин", student.getLastName());
        assertNotNull(student.getGroup());
        assertEquals(1, student.getGroup().getId());
        assertEquals("Java", student.getGroup().getName());
    }

    @Test
    void toDto() {

        Student student = new Student(1, "Вася", "Пупкин",
                LocalDate.of(2000, 1,1),
                new Group(1, "Java summer 2010", null, null));
        StudentDto studentDto = mapper.toDto(student);

        assertNotNull(studentDto);
        assertEquals(studentDto.getFirstName(), "Вася");
        assertEquals(studentDto.getLastName(), "Пупкин");
        assertEquals(studentDto.getLastName(), "Пупкин");
        assertEquals(studentDto.getGroupName(), "Java summer 2010");
        assertEquals(studentDto.getId(), 1);
    }

}
