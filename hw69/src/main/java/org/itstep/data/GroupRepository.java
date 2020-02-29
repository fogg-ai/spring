package org.itstep.data;

import org.itstep.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class GroupRepository implements org.itstep.data.Repository<Group, Integer> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class)
    @Override
    public Integer save(Group data) {


        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("insert into \"group\" (group_name) values(?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getGroupName());
            return ps;
        }, holder);

        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class)
    @Override
    public void update(Group data) {
        Group group = null;
        System.out.println(data.getId());
        try {
            group = jdbcTemplate.queryForObject("Select id, group_name from \"group\" where id = ?", new Object[]{data.getId()},
                    (ResultSet resultSet, int rowNum) -> new Group(resultSet.getInt("id"),
                            resultSet.getString("group_name")));
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }

        Group groupFinal = group;
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE \"group\" set group_name = ? where id = ?", Statement.NO_GENERATED_KEYS);
            if ("".equals(data.getGroupName())) {
                ps.setString(1, groupFinal.getGroupName());
            } else {
                ps.setString(1, data.getGroupName());
            }
            ps.setInt(2, data.getId());
            return ps;
        });
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class)
    @Override
    public boolean delete(Group data) {
        try {
            return jdbcTemplate.update("delete from \"group\" where id = ?", data.getId()) != 0;
        }catch (Exception e){
            return false;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<Group> findAll() {
        return jdbcTemplate.query("select id, group_name from \"group\"",
                (rs, rowNum) -> new Group(rs.getInt("id"),
                        rs.getString("group_name")));
    }
    @Transactional(readOnly = true)
    @Override
    public Group find(Integer integer) {
        Group group = null;
        try {
            group = jdbcTemplate.queryForObject("Select id, group_name from \"group\" where id = ?", new Object[]{integer}, (ResultSet resultSet, int rowNum) ->
                    new Group(resultSet.getInt("id"), resultSet.getString("group_name")));
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }
        return group;
    }


}
