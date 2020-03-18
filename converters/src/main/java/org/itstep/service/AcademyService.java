package org.itstep.service;

import org.itstep.data.Dao;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcademyService {

    final Dao<Student, Integer> studentDao;

    @Autowired
    public AcademyService(@Qualifier("studentDaoImpl") Dao<Student, Integer> studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student getOne(Integer id) {
        return studentDao.getOne(id);
    }

    public Integer save(Student student) {
        return studentDao.save(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public boolean delete(Integer id) {
        return studentDao.deleteById(id);
    }

    public List<String> getGroups() {
        return null;
    }
}
