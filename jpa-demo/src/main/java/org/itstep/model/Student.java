package org.itstep.model;

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
    private String firstName;

    @NotBlank
    @NonNull
    @Length(max = 50)
    private String lastName;

    @Past
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

}
