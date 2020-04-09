package org.itstep.service.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class StudentDto {

    private Integer id;

    @NotBlank
    @NonNull
    private String firstName;

    @NotBlank
    @NonNull
    private String lastName;

    @NotBlank
    @NonNull
    private String studentName;

    @NotBlank
    @NonNull
    private String password;

    @NotBlank
    @NonNull
    private String role;

    @Past
    @NotNull
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NotNull
    @NonNull
    private Integer groupId;

    private String groupName;
}
