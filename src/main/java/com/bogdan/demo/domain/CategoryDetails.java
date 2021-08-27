package com.bogdan.demo.domain;

import com.bogdan.demo.enums.CategoryDescription;
import com.bogdan.demo.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CategoryDetails {

    @NotNull
    private CategoryType categoryType;

    private CategoryDescription categoryDescription;

    private int friendShipYears;

}
