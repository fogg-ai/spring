package org.itstep.data;

import org.itstep.dto.StudentDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.StudentRepository;
import org.itstep.service.AcademyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

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
    AcademyService academyService;

    @Test
    void getStudentDto() {
        when(studentRepository.findById(1))
                .thenReturn(Optional.of(new Student(1, "Вася", "Пупкин",
                        LocalDate.of(2001, 1, 1),
                        new Group(1, "Java summer 2019", null, null))));

        StudentDto studentDto = academyService.getStudentDto(1);

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
