package com.bogdan.demo.resources;

import com.bogdan.demo.annotations.NameValidator;
import com.bogdan.demo.domain.PhoneBookDTO;
import com.bogdan.demo.domain.PhoneBookResponse;
import com.bogdan.demo.domain.PhoneBookResponseDTO;
import com.bogdan.demo.service.PhoneBookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/agenda", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class PhoneBookResource {

    private final PhoneBookService phoneBookService;

    public PhoneBookResource(final PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping
    public ResponseEntity<PhoneBookResponse> getAllContacts() {
        final List<PhoneBookResponseDTO> contacts = phoneBookService.getAllContactsFilteredBySurName();
        if (contacts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(new PhoneBookResponse(contacts));
        }
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<PhoneBookResponseDTO> getContactBySurName(@NotNull(message = "The sur name must be specified") @RequestParam(name = "surName")
                                                                    @NameValidator final String surName) {
        return ResponseEntity.ok(phoneBookService.findContactBySurName(surName));
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@categoryValidatorService.didFulfillCategory(#phoneBookDTO.category)")
    public ResponseEntity<PhoneBookResponseDTO> addNewContact(@RequestBody @Valid PhoneBookDTO phoneBookDTO) {
        return ResponseEntity.ok(phoneBookService.saveContact(phoneBookDTO));
    }

    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@categoryValidatorService.didFulfillCategory(#phoneBookDTO.category)")
    public ResponseEntity<PhoneBookResponseDTO> editContact(@RequestBody @Valid PhoneBookDTO phoneBookDTO) {
        return ResponseEntity.ok(phoneBookService.editContact(phoneBookDTO));
    }

    @DeleteMapping(path = "/edit")
    public ResponseEntity<Void> deleteContact(@RequestParam(name = "surName") @NameValidator final String surName) {
        phoneBookService.deleteContact(surName);
        return ResponseEntity.noContent().build();
    }

}
