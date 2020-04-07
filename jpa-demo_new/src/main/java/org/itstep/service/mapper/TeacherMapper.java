package org.itstep.service.mapper;

import org.itstep.domain.Group;
import org.itstep.domain.Teacher;
import org.itstep.service.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDto, Teacher> {

    @Mapping(target = "removeGroups", ignore = true)
    Teacher toEntity(TeacherDto teacherDTO);

    @Mapping(target = "groupsId", source = "groups")
    TeacherDto toDto(Teacher teacher);

    List<Integer> mapGroupsToGroupsId(List<Group> groups);

    default Integer mapGroupsToInteger(Group group) {
        return group.getId();
    }

    default Teacher fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
