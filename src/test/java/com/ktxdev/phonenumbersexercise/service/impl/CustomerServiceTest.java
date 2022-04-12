package com.ktxdev.phonenumbersexercise.service.impl;

import com.ktxdev.phonenumbersexercise.dao.CustomerDao;
import com.ktxdev.phonenumbersexercise.models.Customer;
import com.ktxdev.phonenumbersexercise.models.PhoneState;
import com.ktxdev.phonenumbersexercise.service.CountryService;
import com.ktxdev.phonenumbersexercise.service.CustomerService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CountryService countryService;

    @Mock
    private CustomerDao customerDao;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerServiceImpl(customerDao, countryService);
    }

    @Test
    void whenGetAllCustomers_thenShouldCallCustomerDaoFindAll() {
        // Given
        String country = "";
        String countryCode = "";
        String phone = "";

        // When
        underTest.getAllCustomers(country, countryCode, null, phone);

        // Then
        verify(customerDao).findAllCustomers(country, countryCode, phone);
    }

    @Test
    void givenValidPhoneState_whenGetAllCustomers_shouldReturnCustomersWithValidPhoneNumbers() {
        // Given
        String country = "";
        String countryCode = "";
        String phone = "";
        PhoneState state = PhoneState.VALID;

        String validPhone = "(237) 673122155";
        String inValidPhone = "(237) 6A0311634";

        when(customerDao.findAllCustomers(country, countryCode, phone))
                .thenReturn(Arrays.asList(
                        new Customer(1, "ARREYMANYOR ROLAND TABOT", "(237) 6A0311634"),
                        new Customer(2, "LOUIS PARFAIT OMBES NTSO", "(237) 673122155")
                ));

        when(countryService.isValidPhoneNumber("+237", validPhone))
                .thenReturn(true);
        when(countryService.isValidPhoneNumber("+237", inValidPhone))
                .thenReturn(false);

        // When
        val customers = underTest.getAllCustomers(country, countryCode, state, phone);

        // Then
        assertThat(customers.size()).isEqualTo(1);
        val customer = customers.get(0);
        assertThat(customer.getPhone()).isEqualTo(validPhone);
    }

    @Test
    void givenNotValidPhoneState_whenGetAllCustomers_shouldReturnCustomersWithInValidPhoneNumbers() {
        // Given
        String country = "";
        String countryCode = "";
        String phone = "";
        PhoneState state = PhoneState.INVALID;

        String validPhone = "(237) 673122155";
        String inValidPhone = "(237) 6A0311634";

        when(customerDao.findAllCustomers(country, countryCode, phone))
                .thenReturn(Arrays.asList(
                        new Customer(1, "ARREYMANYOR ROLAND TABOT", "(237) 6A0311634"),
                        new Customer(2, "LOUIS PARFAIT OMBES NTSO", "(237) 673122155")
                ));

        when(countryService.isValidPhoneNumber("+237", validPhone))
                .thenReturn(true);
        when(countryService.isValidPhoneNumber("+237", inValidPhone))
                .thenReturn(false);

        // When
        val customers = underTest.getAllCustomers(country, countryCode, state, phone);

        // Then
        assertThat(customers.size()).isEqualTo(1);
        val customer = customers.get(0);
        assertThat(customer.getPhone()).isEqualTo(inValidPhone);
    }
}