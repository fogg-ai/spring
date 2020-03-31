package org.itstep.repository;

import org.itstep.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    // JPQL
//    @Query("FROM Group g where upper(g.name) like %upper(?1)%")
    @Query("FROM Group g where upper(g.name) like upper(:name)")
    List<Group> groupsLike(@Param("name") String text);

    // SQL
    @Query(value = "SELECT * FROM groups", nativeQuery = true)
    List<Group> getAll();

    // find, get, query...
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
    List<Group> findAllByNameContainsIgnoreCase(String text);
}
