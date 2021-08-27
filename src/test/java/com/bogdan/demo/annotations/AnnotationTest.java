package com.bogdan.demo.annotations;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("When DTO is populated with valid values, expect validations to pass")
    public void expectAllValidationsToPass() {
        testValidation(new SomeDTO("name", "email@gmail.com", "1234555"), true);
    }

    @Test
    @DisplayName("When phone number is not valid, expect one validation to fail")
    public void expectExceptionWhenPhoneNumber_isInvalid() {
        testValidation(new SomeDTO("name", "email@email.com", ""), false);
    }

    @Test
    @DisplayName("When email is not valid, expect one validation to fail")
    public void expectExceptionWhenEmail_isInvalid() {
        testValidation(new SomeDTO("name", "email@", "12334"), false);
    }

    @Test
    @DisplayName("When name is not valid, expect one validation to fail")
    public void expectExceptionWhenName_isInvalid() {
        testValidation(new SomeDTO("", "email@email.com", ""), false);
    }

    private Set<ConstraintViolation<Object>> testValidation(Object someResource, boolean valid) {
        Set<ConstraintViolation<Object>> violations = validator.validate(someResource);
        assertEquals(violations.isEmpty(), valid);
        return violations;
    }

    //TODO: ADD MORE USE CASES


    @AllArgsConstructor
    class SomeDTO {
        @NameValidator
        private String name;
        @EmailValidator
        private String email;
        @PhoneNumberValidator
        private String phoneNumber;
    }
}
