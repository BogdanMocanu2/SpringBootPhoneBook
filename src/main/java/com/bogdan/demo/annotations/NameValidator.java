package com.bogdan.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^.{2,50}$", message = "Name is not valid")
public @interface NameValidator {
    String message() default "-- not used --";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
