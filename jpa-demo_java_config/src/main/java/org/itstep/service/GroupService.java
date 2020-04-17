package org.itstep.service;

import org.itstep.service.dto.GroupDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService extends GenericService<GroupDto> {
    Page<GroupDto> findAllWithEagerRelationships(Pageable pageable);
    List<GroupDto> findAllByIds(List<Integer> ids);
}
