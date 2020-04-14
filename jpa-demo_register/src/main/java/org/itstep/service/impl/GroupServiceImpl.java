package org.itstep.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.Group;
import org.itstep.repository.GroupRepository;
import org.itstep.service.GroupService;
import org.itstep.service.dto.GroupDto;
import org.itstep.service.mapper.GroupMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Group}.
 */
@Slf4j
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    final GroupRepository groupRepository;
    final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDto save(GroupDto groupsDto) {
        log.debug("Request to save Group : {}", groupsDto);
        Group group = groupMapper.toEntity(groupsDto);
        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDto> findAll(Pageable pageable) {
        log.debug("Request to get all Group");
        return groupRepository.findAllWithEagerRelationships(pageable)
                .map(groupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDto> findAllWithEagerRelationships(Pageable pageable) {
        return groupRepository.findAllWithEagerRelationships(pageable).map(groupMapper::toDto);
    }

    @Override
    public List<GroupDto> findAllByIds(List<Integer> ids) {
        return groupRepository.findAllWithEagerRelationshipsById(ids)
                .stream().map(groupMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GroupDto> findOne(Integer id) {
        log.debug("Request to get Group : {}", id);
        return groupRepository.findOneWithEagerRelationships(id)
                .map(groupMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Group : {}", id);
        groupRepository.deleteById(id);
    }
}
