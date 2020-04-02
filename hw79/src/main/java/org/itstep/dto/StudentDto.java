package org.itstep.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
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
    private String photo;

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
