package com.ktxdev.phonenumbersexercise.service.impl;

import com.ktxdev.phonenumbersexercise.dao.CustomerDao;
import com.ktxdev.phonenumbersexercise.models.Customer;
import com.ktxdev.phonenumbersexercise.models.PhoneState;
import com.ktxdev.phonenumbersexercise.service.CountryService;
import com.ktxdev.phonenumbersexercise.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    private final CountryService countryService;

    @Override
    public List<Customer> getAllCustomers(
            String country,
            String countryCode,
            PhoneState state,
            String phone) {
        List<Customer> customers = customerDao.findAllCustomers(country, countryCode, phone);

        if (nonNull(state) && state.equals(PhoneState.VALID)) {

            return customers.stream()
                    .filter(this::isCustomerPhoneNumberValid)
                    .collect(Collectors.toList());
        } else if (nonNull(state) && state.equals(PhoneState.INVALID)) {

            return customers.stream()
                    .filter(customer -> !this.isCustomerPhoneNumberValid(customer))
                    .collect(Collectors.toList());
        }

        return customers;
    }

    private boolean isCustomerPhoneNumberValid(Customer customer) {
        String customerPhone = customer.getPhone();
        String code = "+" + customerPhone.substring(1, 4);
        return countryService.isValidPhoneNumber(code, customerPhone);
    }
}
