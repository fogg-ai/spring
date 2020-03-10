package org.itstep.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CheckNumberPhoneClass implements ConstraintValidator<CheckNumberPhone, String> {
    CheckNumberPhone numberPhone;

    @Override
    public void initialize(CheckNumberPhone constraintAnnotation) {
        this.numberPhone = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return Pattern.matches("((^\\+(\\D*\\d){12}$)|(^(\\D*\\d){10}$)|(^(\\D*\\d){12}$))",value);
    }
}
