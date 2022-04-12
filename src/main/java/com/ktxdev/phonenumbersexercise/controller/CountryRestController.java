package com.ktxdev.phonenumbersexercise.controller;

import com.ktxdev.phonenumbersexercise.models.Country;
import com.ktxdev.phonenumbersexercise.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/countries")
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }
}
