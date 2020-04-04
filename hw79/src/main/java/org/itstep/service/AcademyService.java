package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.dto.TeacherDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.model.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AcademyService {

    final StudentRepository studentRepository;
    final GroupRepository groupRepository;
    final TeacherRepository teacherRepository;

    @Autowired
    public AcademyService(StudentRepository studentRepository, GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsDto(Integer size) {
        return studentRepository.findAll().stream().filter(student -> student.getId() <= size && student.getId() >= size - 4)
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(),s.getPhoto().substring(s.getPhoto().lastIndexOf("/") + 1), s.getBirthDate(),
                        s.getGroup().getId(), s.getGroup().getName()))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentsForName(String firstName,String lastName) {
        return studentRepository.findAll().stream().filter(student -> student.getFirstName().equals(firstName)
                || student.getLastName().equals(lastName))
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(), s.getPhoto().substring(s.getPhoto().lastIndexOf("/") + 1), s.getBirthDate(),
                        s.getGroup().getId(), s.getGroup().getName()))
                .collect(Collectors.toList());
    }

    public List<StudentDto> findStudentsForNameAndLastName(String firstName,String lastName) {
        return studentRepository.findAll().stream().filter(student -> student.getFirstName().equals(firstName)
                && student.getLastName().equals(lastName))
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName(), s.getPhoto().substring(s.getPhoto().lastIndexOf("/") + 1), s.getBirthDate(),
                        s.getGroup().getId(), s.getGroup().getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Integer save(Student student) {
        return studentRepository.saveAndFlush(student).getId();
    }

    public void update(Student student) {
        studentRepository.save(student);
    }

    public boolean delete(Integer id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public Group getGroup(Integer id) {


        return groupRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public Integer save(Group group) {
        return groupRepository.saveAndFlush(group).getId();
    }

    public void saveAll(List<Group> groups) {
        for (Group g : groups) {
            save(g);
        }
    }

    public void update(Group group) {
        groupRepository.save(group);
    }

    public boolean deleteGroup(int id) {
        groupRepository.deleteById(id);
        return true;
    }

    public void save(StudentDto s)  {
        Group g = groupRepository.findById(s.getGroupId()).orElse(null);
        Student student = new Student(s.getFirstName(), s.getLastName(), s.getPhoto(), s.getBirthDate(), g);
        save(student);
    }

    public void update(StudentDto studentDto) throws IOException {
        save(new Student(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getPhoto(),studentDto.getBirthDate(),
                getGroup(studentDto.getGroupId())));
    }

    public List<GroupDto> findGroupsDto(Integer size) {
        return groupRepository.findAll()
                .stream().filter(group -> group.getId() <= size && group.getId() >= size - 4).map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }
    public List<GroupDto> findGroupsDto() {
        return groupRepository.findAll()
                .stream().map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    public List<GroupDto> findGroupsForName(String name) {
        return groupRepository.findAll()
                .stream().filter(group -> group.getName().equals(name)).map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentDto(int id) {
        Student student = getStudent(id);
        log.debug(student.toString());
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getPhoto(), student.getBirthDate(),
                student.getGroup().getId(), student.getGroup().getName());

    }

    public GroupDto getGroupDto(int id) {
        Group group = getGroup(id);
        return new GroupDto(group.getId(), group.getName());
    }

    public void update(GroupDto groupDto) {
        Group g = getGroup(groupDto.getId());
        g.setName(groupDto.getName());
        save(g);
    }

    public void save(GroupDto groupDto) {
        save(new Group(groupDto.getName()));
    }

    public Integer save(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher).getId();
    }

    public void save(TeacherDto teacherDto) {
        throw new RuntimeException("Not implemented yet");
    }

    public boolean deleteTeacher(int id) {
        teacherRepository.deleteById(id);
        return true;
    }

    public Teacher getTeacherDto(int id) {
        throw new RuntimeException("Not implemented yet");
    }

    public void update(TeacherDto teacherDto) {
        throw new RuntimeException("Not implemented yet");
    }

    public List<TeacherDto> findAllTeachers(Integer size) {
        return teacherRepository.findAll().stream().filter(teacher -> teacher.getId() <= size && teacher.getId() >= size - 4)
                .map(t -> new TeacherDto(t.getId(), t.getFirstName(), t.getLastName(),
                        t.getGroups().stream().map(Group::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
    public List<TeacherDto> findAllTeachersForName(String firstName,String lastName) {
        return teacherRepository.findAll().stream().filter(teacher -> teacher.getFirstName().equals(firstName)
                || teacher.getLastName().equals(lastName))
                .map(t -> new TeacherDto(t.getId(), t.getFirstName(), t.getLastName(),
                        t.getGroups().stream().map(Group::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<TeacherDto> findAllTeachersForNameAndLastName(String firstName,String lastName) {
        return teacherRepository.findAll().stream()
                .filter(teacher -> teacher.getFirstName().equals(firstName))
                .filter(teacher -> teacher.getLastName().equals(lastName))
                .map(t -> new TeacherDto(t.getId(), t.getFirstName(), t.getLastName(),
                        t.getGroups().stream().map(Group::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
