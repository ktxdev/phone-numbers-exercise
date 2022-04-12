package com.ktxdev.phonenumbersexercise.dao;

import com.ktxdev.phonenumbersexercise.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<Country, String> {
    boolean existsByCode(String code);
}
