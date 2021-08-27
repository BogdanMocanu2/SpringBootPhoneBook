package com.bogdan.demo.mappers;

import com.bogdan.demo.domain.CategoryDetails;
import com.bogdan.demo.domain.PhoneBookResponseDTO;
import com.bogdan.demo.entities.PhoneBookEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoMapper {

    public PhoneBookResponseDTO mapResponseFromEntity(final PhoneBookEntity phoneBookEntity) {
        return PhoneBookResponseDTO.builder()
                .surName(phoneBookEntity.getSurName())
                .name(phoneBookEntity.getName())
                .phoneNumber(phoneBookEntity.getPhoneNumber())
                .eMail(phoneBookEntity.getEmail())
                .category(CategoryDetails.builder()
                        .categoryDescription(phoneBookEntity.getCategory().getCategoryDescription())
                        .categoryType(phoneBookEntity.getCategory().getCategoryType())
                        .friendShipYears(phoneBookEntity.getCategory().getFriendshipYears())
                        .build())
                .build();
    }

}
