package com.ktxdev.phonenumbersexercise.service;

import com.ktxdev.phonenumbersexercise.dao.CountryDao;
import com.ktxdev.phonenumbersexercise.exceptions.ResourceNotFoundException;
import com.ktxdev.phonenumbersexercise.models.Country;
import com.ktxdev.phonenumbersexercise.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceUnitTest {

    @Mock
    private CountryDao countryDao;

    private CountryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CountryServiceImpl(countryDao);
    }

    @Test
    void whenGetAllCountries_shouldReturnAllCountries() {
        // When
        underTest.getAllCountries();
        // then
        verify(countryDao).findAll();
    }

    @Test
    void givenValidPhoneNumber_whenIsValidPhoneNumber_shouldReturnTrue() {
        // Given
        String countryCode = "+251";
        Country country = new Country(countryCode, "Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$");
        when(countryDao.findById(countryCode)).thenReturn(Optional.of(country));
        String phoneNumber = "(251) 911203317";

        // When
        boolean isValid = underTest.isValidPhoneNumber(countryCode, phoneNumber);

        // Then
        verify(countryDao).findById(countryCode);
        assertThat(isValid).isTrue();
    }

    @Test
    void givenInvalidPhoneNumber_whenIsValidPhoneNumber_shouldReturnFalse() {
        // Given
        String countryCode = "+251";
        Country country = new Country(countryCode, "Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$");
        when(countryDao.findById(countryCode)).thenReturn(Optional.of(country));
        String phoneNumber = "(251) 9112033179";

        // When
        boolean isValid = underTest.isValidPhoneNumber(countryCode, phoneNumber);

        // Then
        verify(countryDao).findById(countryCode);
        assertThat(isValid).isFalse();
    }

    @Test
    void givenNonExistentCountryCode_whenIsValidPhoneNumber_shouldThrow() {
        // Given
        String countryCode = "+251";
        when(countryDao.findById(countryCode)).thenReturn(Optional.empty());
        String phoneNumber = "(251) 9112033179";

        // Then
        assertThatThrownBy(() -> underTest.isValidPhoneNumber(countryCode, phoneNumber))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Country with code: " + countryCode + " not found");
    }
}