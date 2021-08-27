package com.bogdan.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Email is not valid")
public @interface EmailValidator {

    String message() default "{javax.validation.constraints.Pattern.message}";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

