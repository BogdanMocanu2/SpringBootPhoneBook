package com.bogdan.demo.validators;

import com.bogdan.demo.enums.CategoryType;
import com.bogdan.demo.exceptions.InvalidParameterException;
import org.springframework.core.convert.converter.Converter;

public class CategoryTypeValidator implements Converter<String, CategoryType> {

    @Override
    public CategoryType convert(String source) {
        try {
            return CategoryType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException("The specified category type is invalid!", e);
        }
    }

}
