package org.itstep.data;

import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

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

    @Override
    public Integer save(Student data) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("insert into students(first_name, last_name, age, `group`) values(?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getFirstName());
            ps.setString(2, data.getLastName());
            ps.setInt(3, data.getAge());
            ps.setString(4, data.getGroup());
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    @Override
    public void update(Student data) {
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject("Select id, first_name, last_name, age, `group` from students where id = ?", new Object[]{data.getId()}, (ResultSet resultSet, int rowNum) -> new Student(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getString("group")));
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }

        Student finalStudent = student;
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE students set first_name = ?, last_name = ?, age = ?, `group` = ? where id = ?",Statement.NO_GENERATED_KEYS);
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
            if("".equals(data.getGroup())) {
                ps.setString(4, finalStudent.getGroup());
            }else {
                ps.setString(4, data.getGroup());
            }
            ps.setInt(5, data.getId());
            return ps;
        });
    }

    @Override
    public boolean delete(Student data) {
        return jdbcTemplate.update("delete from students where id = ?", data.getId()) > 0;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select id, first_name, last_name, age, `group` from students",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("group")));
    }

    @Override
    public Student find(Integer id) {
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject("Select id, first_name, last_name, age, `group` from students where id = ?", new Object[]{id}, (ResultSet resultSet, int rowNum) -> new Student(resultSet.getInt("id"),
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
