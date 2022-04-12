package com.ktxdev.phonenumbersexercise.service;

import com.ktxdev.phonenumbersexercise.models.Customer;
import com.ktxdev.phonenumbersexercise.models.PhoneState;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll(String country, String countryCode, PhoneState state, String phone);
}
