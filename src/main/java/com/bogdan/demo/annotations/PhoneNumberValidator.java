package com.bogdan.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^\\+?[0-9 ]{3,25}$", message = "The phone number needs to be between 3 and 25 characters, containing only numbers.")
public @interface PhoneNumberValidator {

    String message() default "The phone number needs to be between 3 and 25 characters, containing only numbers.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
