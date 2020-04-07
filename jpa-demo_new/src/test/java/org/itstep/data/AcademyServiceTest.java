package org.itstep.data;

import org.itstep.service.impl.StudentServiceImpl;
import org.itstep.service.dto.StudentDto;
import org.itstep.domain.Group;
import org.itstep.domain.Student;
import org.itstep.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// Подключаем mockito
@ExtendWith(MockitoExtension.class)
public class AcademyServiceTest {

    // Мокаем репозиторий
    @Mock
    StudentRepository studentRepository;

    // Внедряем репозиторий в сервис
    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    void getStudentDto() {
        when(studentRepository.findById(1))
                .thenReturn(Optional.of(new Student(1, "Вася", "Пупкин",
                        LocalDate.of(2001, 1, 1),
                        new Group(1, "Java summer 2019", null, null))));

        StudentDto studentDto = studentService.findOne(1).get();

        // проверяем, что метод findById репозитория был вызван ровно 1 раз
        verify(studentRepository, times(1)).findById(1);

        // Проверяем DTO
        assertNotNull(studentDto);
        assertEquals(1, studentDto.getId());
        assertEquals("Вася", studentDto.getFirstName());
        assertEquals("Пупкин", studentDto.getLastName());
        assertEquals("Java summer 2019", studentDto.getGroupName());
        assertEquals(1, studentDto.getGroupId());
    }

}
