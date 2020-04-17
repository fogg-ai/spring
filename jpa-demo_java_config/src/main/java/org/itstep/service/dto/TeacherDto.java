package org.itstep.service.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDto {
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String firstName;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String lastName;

    @NotBlank
    @NonNull
    private String teacherName;

    @NotBlank
    @NonNull
    private String password;

    @NotBlank
    @NonNull
    private String role;

    private List<Integer> groupsId;
}
