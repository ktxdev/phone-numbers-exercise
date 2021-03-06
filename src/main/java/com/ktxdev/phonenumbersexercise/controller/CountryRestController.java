package com.ktxdev.phonenumbersexercise.controller;

import com.ktxdev.phonenumbersexercise.models.Country;
import com.ktxdev.phonenumbersexercise.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
