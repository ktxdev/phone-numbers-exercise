package com.ktxdev.phonenumbersexercise.service.impl;

import com.ktxdev.phonenumbersexercise.dao.CountryDao;
import com.ktxdev.phonenumbersexercise.exceptions.ResourceNotFoundException;
import com.ktxdev.phonenumbersexercise.models.Country;
import com.ktxdev.phonenumbersexercise.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public boolean isValidPhoneNumber(String countryCode, String phone) {
        Country country = countryDao.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Country with code: %s not found", countryCode)));

        Pattern pattern = Pattern.compile(country.getPhoneValidationRegex());
        return pattern.matcher(phone).matches();
    }
}
