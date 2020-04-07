package org.itstep.service.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Integer id;

    @NonNull
    @NotBlank
    private String name;

    List<TeacherDto> teachers = new ArrayList<>();
}
