package org.itstep.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "groups")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"students", "teachers"})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @ManyToMany
    private List<Teacher> teachers = new ArrayList<>();

    public Group students(List<Student> students) {
        this.students = students;
        return this;
    }

    public Group addStudents(Student student) {
        this.students.add(student);
        student.setGroup(this);
        return this;
    }

    public Group removeStudents(Student student) {
        this.students.remove(student);
        student.setGroup(null);
        return this;
    }

    public Group teachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Group addTeachers(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getGroups().add(this);
        return this;
    }

    public Group removeTeachers(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getGroups().remove(this);
        return this;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
