package com.bogdan.demo.services;

import com.bogdan.demo.domain.CategoryDetails;
import com.bogdan.demo.enums.CategoryType;
import com.bogdan.demo.exceptions.InvalidParameterException;
import com.bogdan.demo.service.CategoryValidatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryValidatorServiceTest {

    private final CategoryValidatorService categoryValidatorService = new CategoryValidatorService();

    @Test
    @DisplayName("When having category acquaintance, expect true to be returned")
    public void testFirstCategory() {
        final CategoryDetails categoryDetails = CategoryDetails.builder().categoryType(CategoryType.acquaintance).build();
        assertThat(categoryValidatorService.didFulfillCategory(categoryDetails)).isTrue();
    }

    @Test
    @DisplayName("When category type is family, and description is not present, expect exception")
    public void testFamilyCategory() {
        final CategoryDetails categoryDetails = CategoryDetails.builder().categoryType(CategoryType.family).build();
        assertThrows(InvalidParameterException.class, () -> categoryValidatorService.didFulfillCategory(categoryDetails));
    }

    @Test
    @DisplayName("Whem category type is friends, and friendship years is missing, expect exception")
    public void testFriendsCategoryException() {
        final CategoryDetails categoryDetails = CategoryDetails.builder().categoryType(CategoryType.friends).build();
        assertThrows(InvalidParameterException.class, () -> categoryValidatorService.didFulfillCategory(categoryDetails));
    }
}
