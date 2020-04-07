package org.itstep.init;

import org.itstep.domain.Group;
import org.itstep.domain.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.GroupService;
import org.itstep.service.StudentService;
import org.itstep.service.TeacherService;
import org.itstep.service.dto.GroupDto;
import org.itstep.service.dto.StudentDto;
import org.itstep.service.dto.TeacherDto;
import org.itstep.service.mapper.GroupMapper;
import org.itstep.service.mapper.TeacherMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Profile("dev")
@Component
public class InitDatabase {
    final StudentService studentService;
    final GroupService groupService;
    final GroupMapper groupMapper;
    final TeacherService teacherService;
    final GroupRepository groupRepository;
    final TeacherRepository teacherRepository;

    private static boolean inited;
    final private TeacherMapper teacherMapper;

    public InitDatabase(StudentService studentService, GroupService groupService, TeacherService teacherService, GroupMapper groupMapper, GroupRepository groupRepository, TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @PostConstruct
    public void init() {
        if (inited) return;

        // List of groups
        List<GroupDto> groups = List.of(
                new GroupDto("Java summer 2019"),
                new GroupDto("Java summer 2018"),
                new GroupDto("Java summer 2017"),
                new GroupDto("Internet Marketing 2018"),
                new GroupDto("Java summer 2011"),
                new GroupDto("Java summer 2016"),
                new GroupDto("Java summer 2020"),
                new GroupDto("Web autumn 2011"),
                new GroupDto("Web autumn 2012"),
                new GroupDto("Web autumn 2013"),
                new GroupDto("Web autumn 2014"),
                new GroupDto("Web autumn 2015"),
                new GroupDto("Web autumn 2016"),
                new GroupDto("Web autumn 2017"),
                new GroupDto("Web autumn 2018")
        );
        List<Group> groupList = groups.stream().map(groupMapper::toEntity).collect(Collectors.toList());
        // Init groups
        groups.forEach(groupService::save);
//        groupRepository.saveAll(groupList);

        // Init students
        studentService.save(new StudentDto("Вася", "Пупкин",
                LocalDate.of(2001, 1, 1), 1));

        studentService.save(new StudentDto("Маша", "Ефросинина",
                LocalDate.of(1986, 2, 12), 1));

        // Init teachers
        TeacherDto teacherDto = new TeacherDto(null, "Василий", "Петрович", List.of(1, 2));
//        Teacher teacher = teacherMapper.toEntity(teacherDto);
//        groupList = groupRepository.findAllWithEagerRelationshipsById(teacherDto.getGroupsId());
//        teacher.addGroups(groupList.get(0));
//        teacher.addGroups(groupList.get(1));
//
//        teacherRepository.save(teacher);
//        groupRepository.saveAll(groupList.stream().limit(2).collect(Collectors.toList()));
        teacherService.save(teacherDto);

        inited = true;
    }
}
