package org.itstep.service;

import org.itstep.dao.GroupDao;
import org.itstep.dao.StudentDao;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcademyService {

    final StudentDao studentDao;
    final GroupDao groupDao;

    @Autowired
    public AcademyService(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
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

    public List<Group> getGroups() {
        return groupDao.findAll();
    }

    public Group getOneGroups(Integer id) {
        return groupDao.getOne(id);
    }

    public Integer saveGroup(Group group) {
        return groupDao.save(group);
    }

    public void updateGroup(Group group) {
        groupDao.update(group);
    }

    public boolean deleteGroup(Integer id) {
        return groupDao.deleteById(id);
    }
}