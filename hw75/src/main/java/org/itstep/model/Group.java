package org.itstep.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NonNull
    @NotBlank
    @Column(name = "name_group")
    private String name;
}
