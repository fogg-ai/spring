package org.itstep.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    List<Group> groups;
}
