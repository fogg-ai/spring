package org.itstep.data;

import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao implements Dao<Student, Integer> {

    public static final String QUERY_STUDENTS = "SELECT * FROM students";
    public static final String QUERY_STUDENT_BY_ID = QUERY_STUDENTS + " WHERE id=:id";
    public static final String UPDATE = "UPDATE students SET " +
                                                "first_name=:firstName" +
                                                ", last_name=:lastName" +
                                                ", age=:age" +
                                                ", \"group\"=:group" +
                                                ", birth_date=:birthDate" +
                                                " WHERE id=:id";
    public static final String DELETE_BY_ID = "DELETE FROM students WHERE id=:id";

    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    final private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public StudentDao(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("students")
                .usingColumns("first_name"
                            , "last_name"
                            , "age"
                            , "\"group\""
                            , "birth_date"
                ).usingGeneratedKeyColumns("id");
    }

    @Transactional
    @Override
    public Integer save(Student data) {
        return simpleJdbcInsert.executeAndReturnKey(Map.of("first_name", data.getFirstName(),
                "last_name", data.getLastName(),
                "age", data.getAge(),
                "\"group\"", data.getGroup()
                , "birth_date", data.getBirthDate()
                )).intValue();
    }

    @Transactional
    @Override
    public void update(Student data) {
        namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(data));
    }

    @Override
    public boolean delete(Student data) {
        return deleteById(data.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return namedParameterJdbcTemplate.query(QUERY_STUDENTS, new BeanPropertyRowMapper<>(Student.class));
    }

    @Transactional(readOnly = true)
    @Override
    public Student getOne(Integer id) {
        return namedParameterJdbcTemplate.queryForObject(QUERY_STUDENT_BY_ID,
                Map.of("id", id),
                new BeanPropertyRowMapper<>(Student.class));
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, Map.of("id", id)) != 0;
    }
}
