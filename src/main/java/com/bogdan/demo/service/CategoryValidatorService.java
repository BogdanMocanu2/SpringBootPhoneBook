package com.bogdan.demo.service;

import com.bogdan.demo.domain.CategoryDetails;
import com.bogdan.demo.enums.CategoryType;
import com.bogdan.demo.exceptions.InvalidParameterException;
import org.springframework.stereotype.Service;

@Service
public class CategoryValidatorService {

    public boolean didFulfillCategory(final CategoryDetails categoryDetails) {
        if (categoryDetails.getCategoryType().getField().equalsIgnoreCase(CategoryType.acquaintance.getField())) {
            return true;
        } else if (categoryDetails.getCategoryType().getField().equalsIgnoreCase(CategoryType.family.getField())) {
            if (categoryDetails.getCategoryDescription() == null) {
                throw new InvalidParameterException("The category description needs to be fulfilled.");
            }
        } else if (categoryDetails.getCategoryType().getField().equalsIgnoreCase(CategoryType.friends.getField())) {
            if (categoryDetails.getFriendShipYears() <= 0) {
                throw new InvalidParameterException("The friendship years needs to be specified");
            }
        }
        return true;
    }

}
