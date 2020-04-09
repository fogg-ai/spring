package org.itstep.data;

import org.itstep.domain.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class CriteriaBuilderTest {

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @Transactional
    void initEntityManager() {
        entityManager.persist(new Group("Java summer 2019"));
        entityManager.persist(new Group("Java summer 2020"));
        entityManager.persist(new Group("Web autumn 2019"));
    }

    @Test
    @Transactional
    @DirtiesContext
    void test() {
        assertNotNull(entityManager);

        // JPQL = "FROM Group"
        // groups = entityManager.createQuery("FROM Group g where g.name like :name", Group.class).getResultList();
        List<Group> groups = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> q = cb.createQuery(Group.class);
        Root<Group> from = q.from(Group.class);
        CriteriaQuery<Group> where = q.where(cb.like(from.get("name"), "Java%"));
        groups = entityManager.createQuery(where).getResultList();
        assertNotNull(groups);
        assertEquals(2, groups.size());
    }
}
