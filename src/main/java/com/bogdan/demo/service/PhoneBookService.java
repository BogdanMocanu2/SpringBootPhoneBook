package com.bogdan.demo.service;

import com.bogdan.demo.domain.PhoneBookDTO;
import com.bogdan.demo.domain.PhoneBookResponseDTO;
import com.bogdan.demo.entities.PhoneBookEntity;
import com.bogdan.demo.exceptions.ContactNotFoundException;
import com.bogdan.demo.exceptions.OveridingContactException;
import com.bogdan.demo.mappers.DtoToEntityMapper;
import com.bogdan.demo.mappers.EntityToDtoMapper;
import com.bogdan.demo.repository.PhoneBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneBookService {

    private final PhoneBookRepository phoneBookRepository;
    // TODO: In future versions use the spring boot mappers to do the mapping automatically.
    private final EntityToDtoMapper entityToDtoMapper;
    private final DtoToEntityMapper dtoToEntityMapper;

    public PhoneBookService(final PhoneBookRepository phoneBookRepository,
                            final EntityToDtoMapper entityToDtoMapper,
                            final DtoToEntityMapper dtoToEntityMapper) {
        this.phoneBookRepository = phoneBookRepository;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public List<PhoneBookResponseDTO> getAllContactsFilteredBySurName() {
        return phoneBookRepository.findAllByOrderBySurNameAsc()
                .stream()
                .map(entityToDtoMapper::mapResponseFromEntity)
                .collect(Collectors.toList());
    }

    public PhoneBookResponseDTO findContactBySurName(final String surName) {
        return entityToDtoMapper.mapResponseFromEntity(phoneBookRepository.findBySurName(surName)
                .orElseThrow(() -> new ContactNotFoundException(String
                        .format("Contact having sur name %s was not found", surName))));
    }

    public void deleteContact(final String surName) {
        final PhoneBookEntity contact = phoneBookRepository.findBySurName(surName)
                .orElseThrow(() -> new ContactNotFoundException(String
                        .format("Could not delete contact with name: %s . The contact does not exists.", surName)));
        phoneBookRepository.delete(contact);
    }

    public PhoneBookResponseDTO saveContact(final PhoneBookDTO phoneBookDTO) {
        final Optional<PhoneBookEntity> optionalContact = phoneBookRepository.findBySurNameAndPhoneNumber(phoneBookDTO.getSurName(), phoneBookDTO.getPhoneNumber());
        if (optionalContact.isEmpty()) {
            return entityToDtoMapper
                    .mapResponseFromEntity(phoneBookRepository
                            .save(dtoToEntityMapper.mapToEntity(phoneBookDTO)));
        } else {
            throw new OveridingContactException(String.format("A contact with sur name %s and phone number %s already exists. If you want to edit it, choose the PUT method.", phoneBookDTO.getSurName(), phoneBookDTO.getPhoneNumber()));
        }
    }

    public PhoneBookResponseDTO editContact(final PhoneBookDTO phoneBookDTO) {
        phoneBookRepository.findBySurName(phoneBookDTO.getSurName())
                .orElseThrow(() ->
                        new ContactNotFoundException(String
                                .format("Contact having sur name %s was not found", phoneBookDTO.getSurName())));
        return entityToDtoMapper
                .mapResponseFromEntity(phoneBookRepository
                        .save(dtoToEntityMapper.mapToEntity(phoneBookDTO)));
    }

}
