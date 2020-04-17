package org.itstep.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.itstep.service.StudentService;
import org.itstep.service.dto.GroupDto;
import org.itstep.service.dto.StudentDto;
import org.itstep.service.dto.TeacherDto;
import org.itstep.domain.Group;
import org.itstep.domain.Student;
import org.itstep.domain.Teacher;
import org.itstep.repository.GroupRepository;
import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.itstep.service.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;
    final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto save(StudentDto dto) {
        log.debug("Request to save Student : {}", dto);
        Student student = studentMapper.toEntity(dto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Page<StudentDto> findAll(Pageable pageable) {
        log.debug("Request to get all Students");
        return studentRepository.findAll(pageable)
                .map(studentMapper::toDto);
    }

    @Override
    public Optional<StudentDto> findOne(Integer id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }
}
