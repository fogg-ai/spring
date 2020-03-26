package org.itstep.data;


import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.dto.TeacherDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.model.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.AcademyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestAcademyServise {
    @Mock
    StudentRepository studentRepository;
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    GroupRepository groupRepository;

    @InjectMocks
    AcademyService academyService;


    @Test
    void getStudentDto(){
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(new Teacher(1,"Walentin","Masfo",LocalDate.of(1987, 3, 12),null));

        when(studentRepository.findById(1))
                .thenReturn(Optional.of(new Student(1, "Dima", "Dquem",
                        LocalDate.of(1987, 3, 12),
                        new Group(1,"Java",null,teachers))));

        when(studentRepository.findById(2))
                .thenReturn(Optional.of(new Student(2, "Vasya", "Pupcin",
                        LocalDate.of(1999, 2, 16),
                        new Group(2,"C++",null,null))));

        StudentDto studentDto = academyService.getStudentDto(1);
        StudentDto studentDtoTwo = academyService.getStudentDto(2);

        verify(studentRepository, times(1)).findById(1);
        verify(studentRepository, times(1)).findById(2);



        assertNotNull(studentDto);
        assertEquals(1, studentDto.getId());
        assertEquals("Dima", studentDto.getFirstName());
        assertEquals("Dquem", studentDto.getLastName());
        assertEquals(LocalDate.of(1987, 3, 12), studentDto.getBirthDate());
        assertEquals("Java", studentDto.getGroupName());

        assertNotNull(studentDtoTwo);
        assertEquals(2, studentDtoTwo.getId());
        assertEquals("Vasya", studentDtoTwo.getFirstName());
        assertEquals("Pupcin", studentDtoTwo.getLastName());
        assertEquals(LocalDate.of(1999, 2, 16), studentDtoTwo.getBirthDate());
        assertEquals("C++", studentDtoTwo.getGroupName());
    }

    @Test
    void getGroup(){
        List<Student> students = new ArrayList<>();
        Set<Teacher> teachers = new HashSet<>();

        students.add(new Student(1, "Vasuj", "Hefen",
                LocalDate.of(1990, 3, 12),new Group(1,"Java",null,null)));

        teachers.add(new Teacher(1,"Walentin","Masfo",
                LocalDate.of(1970, 3, 12),null));


        when(groupRepository.findById(1))
                .thenReturn(Optional.of(new Group(1,"Java",null,null)));

        when(groupRepository.findById(2))
                .thenReturn(Optional.of(new Group(2,"Js",students,teachers)));

        GroupDto groupDto = academyService.getGroupDto(1);
        GroupDto groupDtoTwo = academyService.getGroupDto(2);

        verify(groupRepository, times(1)).findById(1);


        verify(groupRepository, times(1)).findById(2);



        assertNotNull(groupDto);
        assertEquals(1, groupDto.getId());
        assertEquals("Java", groupDto.getName());

        assertNotNull(groupDtoTwo);
        assertEquals(2, groupDtoTwo.getId());
        assertEquals("Js", groupDtoTwo.getName());
    }
    @Test
    void getTeacher(){

        Set<Group> groups = new HashSet<>();
        groups.add(new Group(1,"Java",null,null));


        Set<Group> groupsTwo = new HashSet<>();
        groupsTwo.add(new Group(1,"Java",null,null));
        groupsTwo.add(new Group(2,"C++",null,null));


        when(teacherRepository.findById(1))
                .thenReturn(Optional.of(new Teacher(1, "Walentin", "Masfo",
                        LocalDate.of(2003, 3, 12),
                        groups)));

        when(teacherRepository.findById(2))
                .thenReturn(Optional.of(new Teacher(2, "Misha", "Frank",
                        LocalDate.of(2008, 10, 16),
                        groupsTwo)));


        TeacherDto teacherDto = academyService.getTeacherDto(1);
        TeacherDto teacherDtoTwo = academyService.getTeacherDto(2);

        verify(teacherRepository, times(1)).findById(1);


        verify(teacherRepository, times(1)).findById(2);

        assertNotNull(teacherDto);
        assertEquals(1, teacherDto.getId());
        assertEquals("Walentin", teacherDto.getFirstName());
        assertEquals("Masfo", teacherDto.getLastName());
        assertEquals(LocalDate.of(2003, 3, 12), teacherDto.getWorkExperience());
        assertEquals(groups.stream().filter(data -> Objects.equals(data, new Group(1,"Java",null,null))).findFirst().get().getName(),
                teacherDto.getGroupsName().get(0));


        assertNotNull(teacherDtoTwo);
        assertEquals(2, teacherDtoTwo.getId());
        assertEquals("Misha", teacherDtoTwo.getFirstName());
        assertEquals("Frank", teacherDtoTwo.getLastName());
        assertEquals(LocalDate.of(2008, 10, 16), teacherDtoTwo.getWorkExperience());
        assertEquals(groupsTwo.stream().filter(data -> Objects.equals(data, new Group(1,"Java",null,null))).findFirst().get().getName(),
                teacherDtoTwo.getGroupsName().get(0));
        assertEquals(groupsTwo.stream().filter(data -> Objects.equals(data, new Group(2,"C++",null,null))).findFirst().get().getName(),
                teacherDtoTwo.getGroupsName().get(1));

    }

}
