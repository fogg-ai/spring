package org.itstep.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class StudentDaoImpl implements Dao<Student, Integer> {

    final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Integer save(Student data) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        Integer id = null;
        try {
            id = (Integer) session.save(data);
            //tx.commit();
            log.debug("Saved");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
            tx.rollback();
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public void update(Student data) {

    }

    @Override
    public boolean delete(Student data) {
        return false;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = (List<Student>) sessionFactory.openSession().createQuery("FROM org.itstep.model.Student").list();


        return students;
    }

    @Override
    public Student getOne(Integer integer) {
        return null;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}
