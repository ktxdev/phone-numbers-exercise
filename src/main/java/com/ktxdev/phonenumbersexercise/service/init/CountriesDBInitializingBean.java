package com.ktxdev.phonenumbersexercise.service.init;

import com.ktxdev.phonenumbersexercise.dao.CountryDao;
import com.ktxdev.phonenumbersexercise.models.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CountriesDBInitializingBean implements InitializingBean {

    private final CountryDao countryDao;

    @Override
    public void afterPropertiesSet() {
        List<Country> countries = new ArrayList<>(Arrays.asList(
                new Country("+237", "Cameroon", "\\(237\\)\\?[2368]\\d{7,8}$"),
                new Country("+251", "Ethiopia", "\\(251\\)\\?[1-59]\\d{8}$"),
                new Country("+212", "Morocco", "\\(212\\)\\?[5-9]\\d{8}$"),
                new Country("+258", "Mozambique", "\\(258\\)\\?[28]\\d{7,8}$"),
                new Country("+256", "Uganda", "\\(256\\)\\?\\d{9}$")
        ));

        countries.forEach(country -> {
            if (!countryDao.existsByCode(country.getCode())) {
                countryDao.save(country);
            }
        });
    }
}
