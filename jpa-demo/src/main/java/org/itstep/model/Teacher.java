package org.itstep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;


    private LocalDate workExperience;
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "teachers_group",
//            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
//    )
    private Set<Group> groups;


    public Teacher(String firstName, String lastName, LocalDate workExperience, Set<Group> groupSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workExperience = workExperience;
        this.groups = groupSet;
    }
}
