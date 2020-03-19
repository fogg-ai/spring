package org.itstep.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.itstep.dao.AbstractDao;
import org.itstep.dao.StudentDao;
import org.itstep.model.Student;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class StudentDaoImpl extends AbstractDao<Student, Integer> implements StudentDao {

    protected StudentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }
}
