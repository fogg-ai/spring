package org.itstep.repository;

import org.itstep.domain.Group;
import org.itstep.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "select distinct groups from Group groups left join fetch groups.teachers",
            countQuery = "select count(distinct groups) from Group groups")
    Page<Group> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct groups from Group groups left join fetch groups.teachers")
    List<Group> findAllWithEagerRelationships();

    @Query("select distinct groups from Group groups left join fetch groups.teachers where groups.id in :ids")
    List<Group> findAllWithEagerRelationshipsById(@Param("ids") List<Integer> ids);

    @Query("select groups from Group groups left join fetch groups.teachers where groups.id =:id")
    Optional<Group> findOneWithEagerRelationships(@Param("id") Integer id);
}
