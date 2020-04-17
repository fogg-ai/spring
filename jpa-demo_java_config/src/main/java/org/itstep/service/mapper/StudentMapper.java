package org.itstep.service.mapper;

import org.itstep.domain.Student;
import org.itstep.service.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface StudentMapper extends EntityMapper<StudentDto, Student> {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "group.name", target = "groupName")
    StudentDto toDto(Student student);

    @Mapping(source = "groupId", target = "group")
    Student toEntity(StudentDto studentDto);
}
