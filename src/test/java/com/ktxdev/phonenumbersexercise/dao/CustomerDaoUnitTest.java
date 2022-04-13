package com.ktxdev.phonenumbersexercise.dao;

import com.ktxdev.phonenumbersexercise.models.Country;
import com.ktxdev.phonenumbersexercise.models.Customer;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerDaoUnitTest {

    @Autowired
    private CustomerDao underTest;

    @Autowired
    private CountryDao countryDao;

    @BeforeEach
    void setup() {
        val country = new Country("+263", "Zimbabwe", "\\(263\\)\\?[5-9]\\d{8}$");
        countryDao.save(country);

        List<Customer> customers = new ArrayList<>(Arrays.asList(
                new Customer(1, "Sean Huvaya", "(263) 773806130"),
                new Customer(2, "Tinashe McDonalds", "(264) 784806130"),
                new Customer(3, "Malvern Madovi", "(263) 734806130")
        ));

        underTest.saveAll(customers);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void givenExistingCountryCode_shouldReturnAllCustomersWithPhoneNumbersWithThatCountryCode() {
        // Given
        String countryCode = "+263";

        // When
        val customers = underTest.findAllCustomers(countryCode, "");

        //then
        val expectedTotalElements = 2;
        assertThat(customers.size()).isEqualTo(expectedTotalElements);
        val actual = customers.get(0);
        val expectedStartString = "(" + countryCode.substring(1) + ")";
        assertThat(actual.getPhone()).startsWith(expectedStartString);
    }

    @Test
    void givenNonExistingCountryCode_shouldReturnNoCustomers() {
        // Given
        String countryCode = "+27";

        // when
        val customers = underTest.findAllCustomers(countryCode, "");

        //then
        val expectedTotalElements = 0;
        assertThat(customers.size()).isEqualTo(expectedTotalElements);
    }

    @Test
    void givenExistingPhoneNumber_shouldReturnCustomerWithGivenPhoneNumber() {
        // given
        String phone = "(263) 773806130";
        // when
        val customers = underTest.findAllCustomers("", phone);

        //then
        val expectedTotalElements = 1;
        assertThat(customers.size()).isEqualTo(expectedTotalElements);
        val actual = customers.get(0);
        val expectedStartString = "(263)";
        assertThat(actual.getPhone()).startsWith(expectedStartString);
    }

    @Test
    void givenNonExistingPhoneNumber_shouldReturnCustomerWithGivenPhoneNumber() {
        // given
        String phone = "(27) 773876180";

        // when
        val customers = underTest.findAllCustomers("", phone);

        //then
        val expectedTotalElements = 0;
        assertThat(customers.size()).isEqualTo(expectedTotalElements);
    }
}