package org.itstep.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itstep.dao.GroupDao;
import org.itstep.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class GroupDaoImpl  implements GroupDao {
    // extends AbstractDao<Group, Integer>
//    protected GroupDaoImpl(SessionFactory sessionFactory) {
//        super(sessionFactory, Group.class);
//    }


    final SessionFactory sessionFactory;

    @Autowired
    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer save(Group data) {
        Integer id = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            id = (Integer) session.save(data);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(ex.getLocalizedMessage(), ex);
        }
        return id;
    }

    @Override
    public void update(Group data) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(data);
            transaction.commit();
        }catch (Exception ex){
            log.error(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean delete(Group data) {
        boolean check = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            log.debug("delete()");
            session.delete(data);
            check = true;
            transaction.commit();
        }catch (Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(ex.getLocalizedMessage(), ex);
        }
        return check;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = null;
        try (Session session = sessionFactory.openSession()) {
            log.debug("Session opened");
            groups = session.createQuery("from " + Group.class.getSimpleName(), Group.class).list();
            log.debug("Query by id");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return groups;
    }

    @Override
    public Group getOne(Integer integer) {
        log.debug("Session opened");
        Group group = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            group = session.find(Group.class,integer); // session.persist()
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(ex.getLocalizedMessage(), ex);
        }
        return group;
    }

    @Override
    public boolean deleteById(Integer integer) {
        boolean check = false;
        try {
            Group group = getOne(integer);
            delete(group);
            check = true;
            log.debug("deleted by id");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return check;
    }
}
