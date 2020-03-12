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
@CheckPassword(field = "password",equalsTo = "passwordСonfirmation",message = "Пароли должны совпадать.")
public class RegisterModel {
    @NotBlank(message = "Заполните поле")
    private String login;
    @NotBlank(message = "Заполните поле")
    private String password;
    @NotBlank(message = "Заполните поле")
    private String passwordСonfirmation;
    @NotBlank(message = "Заполните поле")
    @Email
    private String email;
    @CheckNumberPhone(message = "Введите вверный телефон.")
    private String phone;
    @NotBlank
    private String gender;
}
