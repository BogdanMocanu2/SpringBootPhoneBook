package com.bogdan.demo.domain;

import com.bogdan.demo.annotations.EmailValidator;
import com.bogdan.demo.annotations.NameValidator;
import com.bogdan.demo.annotations.PhoneNumberValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneBookDTO {

    @NameValidator
    @NotNull
    private String name;

    @NameValidator
    @NotNull
    private String surName;

    @PhoneNumberValidator
    @NotNull
    private String phoneNumber;

    @EmailValidator
    @NotNull
    private String eMail;

    @NotNull
    private CategoryDetails category;
}
