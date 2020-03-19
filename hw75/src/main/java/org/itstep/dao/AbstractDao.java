package org.itstep.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@Data
public abstract class AbstractDao<T, ID> implements Dao<T, ID> {

    final private SessionFactory sessionFactory;
    final private Class<T> entityClass;

    protected AbstractDao(@Autowired SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public ID save(T entity) {
        log.debug("Session opened");
        ID id = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            id = (ID) session.save(entity); // session.persist()
            log.debug("Saved");
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
    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            log.debug("Session opened");
            session.update(entity); // session.merge()
            transaction.commit();
            log.debug("updated");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean delete(T entity) {
        boolean isOk = false;
        log.debug("Session opened");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            isOk = true;
            log.debug("deleted");
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(ex.getLocalizedMessage(), ex);
        }
        return isOk;
    }

    @Override
    public List<T> findAll() {
        List<T> entity = null;
        try (Session session = sessionFactory.openSession()) {
            log.debug("Session opened");
            entity = session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
            log.debug("Query by id");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return entity;
    }

    @Override
    public T getOne(ID id) {
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            log.debug("Session opened");
            entity = session.find(entityClass, id);// SELECT ... WHERE id=:id
            log.debug("Query by id");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return entity;
    }

    @Override
    public boolean deleteById(ID id) {
        boolean isOk = false;
        try {
            T entity = getOne(id);
            delete(entity);
            isOk = true;
            log.debug("deleted by id");
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return isOk;
    }
}
