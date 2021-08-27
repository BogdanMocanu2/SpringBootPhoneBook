package com.bogdan.demo.resources;

import com.bogdan.demo.domain.CategoryDetails;
import com.bogdan.demo.domain.PhoneBookDTO;
import com.bogdan.demo.domain.PhoneBookResponseDTO;
import com.bogdan.demo.enums.CategoryDescription;
import com.bogdan.demo.enums.CategoryType;
import com.bogdan.demo.mappers.DtoToEntityMapper;
import com.bogdan.demo.repository.PhoneBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "classpath:application.properties")
public class PhoneBookResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    @Test
    @DisplayName("When call get all, expect 204")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetAll_expect_204() {
        final HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        final ResponseEntity<PhoneBookResponseDTO> entity = this.restTemplate.exchange("/agenda", HttpMethod.GET, httpEntity, PhoneBookResponseDTO.class);
        assertThat(entity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("When creating contact, then retrieving it, expect OK")
    public void createAndGetContactWitSuccess() {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PhoneBookDTO body = new PhoneBookDTO("name", "surName", "1223344", "valid@email.com", CategoryDetails.builder().categoryType(CategoryType.family).categoryDescription(CategoryDescription.aunt).build());

        HttpEntity<PhoneBookDTO> httpEntity = new HttpEntity<>(body, headers);
        final ResponseEntity<PhoneBookResponseDTO> entity = createNewContact(httpEntity);

        assertThat(entity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(entity.getBody()).isNotNull();

        // perform get all
        final ResponseEntity<PhoneBookResponseDTO> getEntity = this.restTemplate.exchange("/agenda", HttpMethod.GET, httpEntity, PhoneBookResponseDTO.class);
        assertThat(getEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(getEntity.getBody()).isNotNull();

        // get by sur name
        final ResponseEntity<PhoneBookResponseDTO> getEntityBySurname = this.restTemplate.exchange("/agenda/filter?surName=surName", HttpMethod.GET, httpEntity, PhoneBookResponseDTO.class);
        assertThat(getEntityBySurname.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(getEntityBySurname.getBody()).isNotNull();
    }

    @Test
    @DisplayName("When editing existing contact, expect ok")
    public void editContact_expectOK() {

        PhoneBookDTO savedInstance = new PhoneBookDTO("name", "surName", "1223344", "valid@email.com", CategoryDetails.builder().categoryType(CategoryType.family).categoryDescription(CategoryDescription.aunt).build());
        phoneBookRepository.save(dtoToEntityMapper.mapToEntity(savedInstance));

        PhoneBookDTO editContact = new PhoneBookDTO("nameEdit", "surName", "1223344", "valid@email.com", CategoryDetails.builder().categoryType(CategoryType.family).categoryDescription(CategoryDescription.aunt).build());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PhoneBookDTO> httpEntity = new HttpEntity<>(editContact, headers);
        final ResponseEntity<PhoneBookResponseDTO> entity = this.restTemplate.exchange("/agenda/edit", HttpMethod.PUT, httpEntity, PhoneBookResponseDTO.class);

        assertThat(entity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(entity.getBody()).isNotNull();
    }

    @Test
    @DisplayName("When deleting contact, expect OK")
    public void deleteContactExpectOk() {
        PhoneBookDTO savedInstance = new PhoneBookDTO("name", "surName", "1223344", "valid@email.com", CategoryDetails.builder().categoryType(CategoryType.family).categoryDescription(CategoryDescription.aunt).build());
        phoneBookRepository.save(dtoToEntityMapper.mapToEntity(savedInstance));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PhoneBookDTO> httpEntity = new HttpEntity<>(null, headers);
        final ResponseEntity<Void> entity = this.restTemplate.exchange("/agenda/edit?surName=surName", HttpMethod.DELETE, httpEntity, Void.class);

        assertThat(entity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }


    // TODO: Add more test cases
    private ResponseEntity<PhoneBookResponseDTO> createNewContact(HttpEntity<PhoneBookDTO> httpEntity) {
        return this.restTemplate.exchange("/agenda/add", HttpMethod.POST, httpEntity, PhoneBookResponseDTO.class);
    }


}
