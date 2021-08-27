package com.bogdan.demo.mappers;

import com.bogdan.demo.domain.PhoneBookDTO;
import com.bogdan.demo.entities.CategoryEntity;
import com.bogdan.demo.entities.PhoneBookEntity;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityMapper {

    public PhoneBookEntity mapToEntity(final PhoneBookDTO phoneBookDTO) {
        return PhoneBookEntity.builder()
                .name(phoneBookDTO.getName())
                .email(phoneBookDTO.getEMail())
                .surName(phoneBookDTO.getSurName())
                .phoneNumber(phoneBookDTO.getPhoneNumber())
                .category(CategoryEntity.builder()
                        .categoryDescription(phoneBookDTO.getCategory().getCategoryDescription())
                        .categoryType(phoneBookDTO.getCategory().getCategoryType())
                        .friendshipYears(phoneBookDTO.getCategory().getFriendShipYears())
                        .build())
                .build();
    }
}
