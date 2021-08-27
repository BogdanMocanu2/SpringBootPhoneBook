package com.bogdan.demo.services;

import com.bogdan.demo.domain.PhoneBookDTO;
import com.bogdan.demo.domain.PhoneBookResponseDTO;
import com.bogdan.demo.entities.CategoryEntity;
import com.bogdan.demo.entities.PhoneBookEntity;
import com.bogdan.demo.enums.CategoryDescription;
import com.bogdan.demo.enums.CategoryType;
import com.bogdan.demo.exceptions.ContactNotFoundException;
import com.bogdan.demo.exceptions.OveridingContactException;
import com.bogdan.demo.mappers.DtoToEntityMapper;
import com.bogdan.demo.mappers.EntityToDtoMapper;
import com.bogdan.demo.repository.PhoneBookRepository;
import com.bogdan.demo.service.PhoneBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhoneBookServiceTest {

    @Mock
    private PhoneBookRepository phoneBookRepository;
    @Mock
    private EntityToDtoMapper entityToDtoMapper;
    @Mock
    private DtoToEntityMapper dtoToEntityMapper;

    @InjectMocks
    private PhoneBookService phoneBookService;

    @Test
    @DisplayName("When getting all the contacts with an empty DB, expect empty list")
    public void getAllContacts_fromEmptyDB() {
        when(phoneBookRepository.findAllByOrderBySurNameAsc()).thenReturn(Collections.emptyList());
        verify(entityToDtoMapper, times(0)).mapResponseFromEntity(any());
        assertThat(phoneBookService.getAllContactsFilteredBySurName()).isEmpty();
    }

    @Test
    @DisplayName("When DB has contacts, and get all is called, expect list of contacts")
    public void expectResult_whenCallingGetAll() {
        when(phoneBookRepository.findAllByOrderBySurNameAsc()).thenReturn(Collections.singletonList(dbRecord()));
        assertThat(phoneBookService.getAllContactsFilteredBySurName()).hasSize(1);
        verify(entityToDtoMapper, atLeastOnce()).mapResponseFromEntity(any());
    }

    @Test
    @DisplayName("When find contact by sur name, expect to throw exception")
    public void findBySurName_expectException() {
        when(phoneBookRepository.findBySurName(any())).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () -> phoneBookService.findContactBySurName(any()));
    }

    @Test
    @DisplayName("When find by sur name, expect record")
    public void findByContact_expectRecord() {
        when(phoneBookRepository.findBySurName("SurName")).thenReturn(Optional.of(dbRecord()));
        when(entityToDtoMapper.mapResponseFromEntity(any())).thenReturn(PhoneBookResponseDTO.builder().build());
        assertThat(phoneBookService.findContactBySurName("SurName")).isNotNull();
    }

    @Test
    @DisplayName("When saving contact, expect entity back")
    public void saveContactAndExpectEntity() {
        when(phoneBookRepository.findBySurNameAndPhoneNumber(any(), any())).thenReturn(Optional.empty());
        when(entityToDtoMapper.mapResponseFromEntity(any())).thenReturn(PhoneBookResponseDTO.builder().build());
        assertThat(phoneBookService.saveContact(new PhoneBookDTO())).isNotNull();
    }

    @Test
    @DisplayName("When trying to save on another contact expect exception to be thrown")
    public void expectException_whenSavingExistingContact() {
        when(phoneBookRepository.findBySurNameAndPhoneNumber(any(), any())).thenReturn(Optional.of(dbRecord()));
        assertThrows(OveridingContactException.class, () -> phoneBookService.saveContact(new PhoneBookDTO()));
    }

    @Test
    @DisplayName("Expect ok when deleting a contact")
    public void testDeleteContact() {
        when(phoneBookRepository.findBySurName(any())).thenReturn(Optional.of(dbRecord()));
        phoneBookService.deleteContact(any());
        verify(phoneBookRepository, atLeastOnce()).delete(any());
    }

    @Test
    @DisplayName("Expect exception when deleting unexisting contact")
    public void testDeleteContact_expectException() {
        when(phoneBookRepository.findBySurName(any())).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () -> phoneBookService.deleteContact(any()));
        verify(phoneBookRepository, times(0)).delete(any());
    }

    //TODO: Add more test use cases.

    private PhoneBookEntity dbRecord() {
        return PhoneBookEntity.builder()
                .phoneNumber("111")
                .surName("SurName")
                .name("Name")
                .email("valid@email.com")
                .category(CategoryEntity.builder()
                        .categoryType(CategoryType.family)
                        .categoryDescription(CategoryDescription.aunt)
                        .friendshipYears(0)
                        .build())
                .build();
    }
}
