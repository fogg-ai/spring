package org.itstep.validator.declarative;

import org.itstep.model.RegisterModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterModel registerModel = (RegisterModel) o;
        if(!registerModel.getPassword().equals(registerModel.getPasswordСonfirmation())){
            errors.rejectValue("password","register.password");
        }
    }
}
