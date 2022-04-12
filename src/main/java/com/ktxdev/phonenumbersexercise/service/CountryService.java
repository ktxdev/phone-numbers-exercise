package com.ktxdev.phonenumbersexercise.service;

import com.ktxdev.phonenumbersexercise.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    boolean isValidPhoneNumber(String countryCode, String phone);
}
