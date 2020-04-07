package org.itstep.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.Group;
import org.itstep.domain.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.GroupService;
import org.itstep.service.TeacherService;
import org.itstep.service.dto.TeacherDto;
import org.itstep.service.mapper.GroupMapper;
import org.itstep.service.mapper.TeacherMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    final GroupService groupService;
    final GroupRepository groupRepository;
    final TeacherRepository teacherRepository;
    final TeacherMapper teacherMapper;
    final GroupMapper groupMapper;

    public TeacherServiceImpl(GroupService groupService, GroupRepository groupRepository, TeacherRepository teacherRepository, TeacherMapper teacherMapper, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.groupMapper = groupMapper;
    }

    @Override
    public TeacherDto save(TeacherDto teacherDto) {
        log.debug("Request to save Groups : {}", teacherDto);
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        List<Group> groupList = groupRepository.findAllWithEagerRelationshipsById(teacherDto.getGroupsId());
        for (Group g : groupList) {
            teacher.addGroups(g);
        }
        teacherRepository.save(teacher);
        groupRepository.saveAll(groupList);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public Page<TeacherDto> findAll(Pageable pageable) {
        log.debug("Request to get all Teachers");
        return teacherRepository.findAll(pageable)
                .map(teacherMapper::toDto);
    }

    @Override
    public Optional<TeacherDto> findOne(Integer id) {
        log.debug("Request to get Teacher : {}", id);
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Teacher : {}", id);
        teacherRepository.deleteById(id);
    }
}
