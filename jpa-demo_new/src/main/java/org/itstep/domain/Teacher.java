package org.itstep.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "teacher")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Group> groups = new ArrayList<>();

    public Teacher groups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public Teacher addGroups(Group groups) {
        this.groups.add(groups);
        groups.getTeachers().add(this);
        return this;
    }

    public Teacher removeGroups(Group groups) {
        this.groups.remove(groups);
        groups.getTeachers().remove(this);
        return this;
    }
}
