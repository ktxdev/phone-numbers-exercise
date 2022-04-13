package com.ktxdev.phonenumbersexercise.controller;

import com.ktxdev.phonenumbersexercise.models.Customer;
import com.ktxdev.phonenumbersexercise.models.PhoneState;
import com.ktxdev.phonenumbersexercise.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll(
            @RequestParam(required = false, defaultValue = "") String countryCode,
            @RequestParam(required = false, defaultValue = "") PhoneState state,
            @RequestParam(required = false,  defaultValue = "") String phone
    ) {
        return customerService.getAllCustomers(countryCode, state, phone);
    }


}
