package org.itstep.service.mapper;

import org.itstep.domain.Group;
import org.itstep.service.dto.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface GroupMapper extends EntityMapper<GroupDto, Group> {
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "removeStudents", ignore = true)
    @Mapping(target = "removeTeachers", ignore = true)
    Group toEntity(GroupDto dto);

    default Group fromId(Integer id) {
        if(id == null) {
            return null;
        }
        Group group = new Group();
        group.setId(id);
        return group;
    }
}
