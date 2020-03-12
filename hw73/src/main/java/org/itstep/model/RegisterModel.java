package org.itstep.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.validator.imperative.CheckNumberPhone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModel {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordСonfirmation;
    @NotBlank
    @Email
    private String email;
    @CheckNumberPhone
    private String phone;
    @NotBlank
    private String gender;
}
