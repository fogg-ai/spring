package org.itstep.data;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository implements org.itstep.data.Repository<Student, Integer> {

    //private final static List<Student> storage = new CopyOnWriteArrayList<>();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class)
    @Override
    public Integer save(Student data) {
        List<Group> query = jdbcTemplate.query("select id, group_name from \"group\"",
                (rs, rowNum) -> new Group(rs.getInt("id"),
                        rs.getString("group_name")));
        int groupId = 0;
        for (int i = 0;i < query.size();i++){
            if(query.get(i).getGroupName().equals(data.getGroup())){
                groupId = query.get(i).getId();
            }else {
                System.out.println("Нету id с такой группой.");
            }
        }

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        int finalGroupId = groupId;
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("insert into students(first_name, last_name, age, \"group\") values(?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getFirstName());
            ps.setString(2, data.getLastName());
            ps.setInt(3, data.getAge());
            ps.setInt(4, finalGroupId);
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class)
    @Override
    public void update(Student data) {
        List<Group> query = jdbcTemplate.query("select id, group_name from \"group\"",
                (rs, rowNum) -> new Group(rs.getInt("id"),
                        rs.getString("group_name")));
        int groupId = 0;
        for (int i = 0;i < query.size();i++){
            if(query.get(i).getGroupName().equals(data.getGroup())){
                groupId = query.get(i).getId();
                break;
            }else {
                groupId = 0;
            }
        }

        Student student = null;
        try {
            student = jdbcTemplate.queryForObject("Select id, first_name, last_name, age, \"group\" from students where id = ?", new Object[]{data.getId()}, (ResultSet resultSet, int rowNum) -> new Student(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getString("group")));
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }

        int finalGroupId = groupId;
        Student finalStudent = student;


        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE students set first_name = ?, last_name = ?, age = ?, \"group\" = ? where id = ?",Statement.NO_GENERATED_KEYS);
            if("".equals(data.getFirstName())) {
                ps.setString(1, finalStudent.getFirstName());
            }else {
                ps.setString(1, data.getFirstName());
            }
            if("".equals(data.getLastName())) {
                ps.setString(2, finalStudent.getLastName());
            }else {
                ps.setString(2, data.getLastName());
            }
            if("".equals(data.getAge())) {
                ps.setInt(3, finalStudent.getAge());
            }else {
                ps.setInt(3, data.getAge());
            }
            if(!"".equals(data.getGroup()) && finalGroupId != 0) {
                ps.setString(4, String.valueOf(finalGroupId));
            }else {
                ps.setString(4, finalStudent.getGroup());
            }
            ps.setInt(5, data.getId());
            return ps;
        });

    }
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = SQLException.class, noRollbackFor = FileNotFoundException.class)
    @Override
    public boolean delete(Student data) {
        return jdbcTemplate.update("delete from students where id = ?", data.getId()) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select students.id, first_name, last_name, age, \"group\", group_name from students join \"group\" g on students.\"group\" = g.id",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("group_name")));
    }

    @Transactional(readOnly = true)
    @Override
    public Student find(Integer id) {
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject("Select students.id, first_name, last_name, age, \"group\" from students where id = ?", new Object[]{id}, (ResultSet resultSet, int rowNum) -> new Student(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getString("group")));
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }
        return student;
    }
}
