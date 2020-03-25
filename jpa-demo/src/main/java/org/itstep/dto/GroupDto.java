package org.itstep.dto;

import lombok.*;
import org.itstep.model.Student;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
}
