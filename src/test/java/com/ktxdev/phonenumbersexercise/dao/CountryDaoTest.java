package com.ktxdev.phonenumbersexercise.dao;

import com.ktxdev.phonenumbersexercise.models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryDaoTest {

    @Autowired
    private CountryDao underTest;

    @BeforeEach
    void setUp() {
        Country country = new Country("+263", "Zimbabwe", "\\(263\\)\\?[5-9]\\d{8}$");
        underTest.save(country);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void givenExistingCountryCode_shouldReturnTrue() {
        // Given
        String countryCode = "+263";

        // When
        boolean exists = underTest.existsByCode(countryCode);

        // Then
        assertTrue(exists);
    }

    @Test
    void givenNonExistingCountryCode_shouldReturnTrue() {
        // Given
        String countryCode = "+264";

        // When
        boolean exists = underTest.existsByCode(countryCode);

        // Then
        assertFalse(exists);
    }
}