package org.itstep.validator;


import javax.validation.Constraint;
import javax.validation.Payload;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CheckPasswordConf.class)
@Documented
public @interface CheckPassword {
    public static final String MESSAGE = "{org.itstep.validator.password}";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    String field();

    String equalsTo();
}