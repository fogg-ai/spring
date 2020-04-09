package org.itstep.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Table(name = "students")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @NonNull
    @Length(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @NonNull
    @Length(max = 50)
    @Column(name = "user_name", nullable = false)
    private String studentName;

    @NotBlank
    @NonNull
    @Length(max = 50)
    private String password;

    @NotBlank
    @NonNull
    @Length(max = 50)
    private String role;

    @Past
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_fk", nullable = false) // поле для внешнего ключа
    private Group group;

    public Student group(Group group) {
        this.group = group;
        return this;
    }
}
