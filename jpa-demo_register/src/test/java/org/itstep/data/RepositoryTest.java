package org.itstep.data;

import org.itstep.domain.Group;
import org.itstep.repository.GroupRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@Transactional
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class RepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    void getAllTest() {
        assertNotNull(groupRepository);

        List<Group> all = groupRepository.findAll();
        assertNotNull(all);
        assertEquals(15, all.size());
        Group group = all.get(0);
        assertEquals(1, group.getId());
        assertEquals("Java summer 2019", group.getName());
    }

    @Test
    void getAllSorted() {
        assertNotNull(groupRepository);

        /*
         select group0_.id as id1_0_, group0_.name as name2_0_
            from groups group0_
            order by group0_.name asc
         */
        List<Group> all = groupRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        assertNotNull(all);
        assertEquals(15, all.size());
        Group group = all.get(0);
        assertEquals(4, group.getId());
        assertEquals("Internet Marketing 2018", group.getName());
    }

    @Test
    void getByExample() {
        assertNotNull(groupRepository);
        /*
        select group0_.id as id1_0_, group0_.name as name2_0_
            from groups group0_
                where group0_.name=?
         */
        Group group = groupRepository.findOne(Example.of(new Group("Internet Marketing 2018")))
                .orElse(null);
        assertNotNull(group);
        assertEquals(4, group.getId());
        assertEquals("Internet Marketing 2018", group.getName());
    }

    @Disabled
    @Test
    void getUsingExtendsMethods() {
        assertNotNull(groupRepository);

        /*
        select group0_.id as id1_0_, group0_.name as name2_0_
            from groups group0_
                upper(group0_.name) like upper(?) escape ?
         */
//        List<Group> javaGroups = groupRepository.findAllByNameContainsIgnoreCase("java");
//
//        assertNotNull(javaGroups);
//        assertEquals(6, javaGroups.size());
//        Group group = javaGroups.get(0);
//
//        assertEquals(1, group.getId());
//        assertEquals("Java summer 2019", group.getName());
//
//        group = javaGroups.get(1);
//
//        assertEquals(2, group.getId());
//        assertEquals("Java summer 2018", group.getName());
    }

    @Disabled
    @Test
    void getUsingQueryAnnotation() {
        assertNotNull(groupRepository);

        /*
        select group0_.id as id1_0_, group0_.name as name2_0_
            from groups group0_
                upper(group0_.name) like upper(?) escape ?
         */
//        List<Group> javaGroups = groupRepository.groupsLike("%java%");
//
//        assertNotNull(javaGroups);
//        assertEquals(6, javaGroups.size());
//        Group group = javaGroups.get(0);
//
//        assertEquals(1, group.getId());
//        assertEquals("Java summer 2019", group.getName());
//
//        group = javaGroups.get(1);
//
//        assertEquals(2, group.getId());
//        assertEquals("Java summer 2018", group.getName());
    }

    @Test
    void getPaging() {
        assertNotNull(groupRepository);

        Page<Group> groupPage = groupRepository.findAll(PageRequest.of(0, 5));

        assertEquals(3, groupPage.getTotalPages()); // кол-во страниц
        assertEquals(15, groupPage.getTotalElements()); // общее кол-во элементов

        List<Group> all = groupPage.getContent();
        assertNotNull(all);
        assertEquals(5, all.size());
        Group group = all.get(0);
        assertEquals(1, group.getId());
        assertEquals("Java summer 2019", group.getName());

        assertEquals("Java summer 2011", all.get(4).getName());
    }
}
