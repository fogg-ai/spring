package org.itstep.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.itstep.validator.CheckNumberPhone;
import org.itstep.validator.CheckPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckPassword(field = "password",equalsTo = "passwordСonfirmation")
public class RegisterModel {
    @NotBlank(message = "Заполните поле")
    @Size(min = 5,max = 15,message = "Введите от 5 символов до 15.")
    private String login;
    @NotBlank(message = "Заполните поле")
    private String password;
    @NotBlank(message = "Заполните поле")
    private String passwordСonfirmation;
    @NotBlank(message = "Заполните поле")
    @Email
    private String email;
    @CheckNumberPhone
    private String phone;
    @NotBlank
    private String gender;
}
