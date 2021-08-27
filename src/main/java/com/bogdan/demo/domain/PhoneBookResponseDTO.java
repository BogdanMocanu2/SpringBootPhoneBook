package com.bogdan.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneBookResponseDTO {

    private String name;
    private String surName;
    private String phoneNumber;
    private String eMail;
    private CategoryDetails category;
}
